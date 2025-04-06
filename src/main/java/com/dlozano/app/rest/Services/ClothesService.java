package com.dlozano.app.rest.Services;

import com.dlozano.app.rest.Models.Clothes;
import com.dlozano.app.rest.Repo.ClothesRepo;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClothesService {

    @Autowired
    private ClothesRepo clothesRepo;

    public Page<Clothes> getFilteredClothes(String search, List<String> categories, List<String> sizes,
                                            String location, Float minPrice, Float maxPrice, Pageable pageable) {
        return clothesRepo.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (search != null && !search.isEmpty()) {
                Predicate brandMatch = cb.like(cb.lower(root.get("brand")), "%" + search.toLowerCase() + "%");
                Predicate modelMatch = cb.like(cb.lower(root.get("model")), "%" + search.toLowerCase() + "%");
                predicates.add(cb.or(brandMatch, modelMatch));
            }

            if (categories != null && !categories.isEmpty()) {
                predicates.add(root.get("category").in(categories));
            }

            if (sizes != null && !sizes.isEmpty()) {
                predicates.add(root.get("size").in(sizes));
            }

            if (location != null && !location.isEmpty()) {
                predicates.add(cb.equal(root.get("location"), location));
            }

            if (minPrice != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("price"), minPrice));
            }

            if (maxPrice != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("price"), maxPrice));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        }, pageable);
    }
}
