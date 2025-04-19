package com.dlozano.app.rest.Services;

import com.dlozano.app.rest.Models.DTO.ClotheDTO;
import com.dlozano.app.rest.Models.Clothes;
import com.dlozano.app.rest.Repo.ClothesRepo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClothesService {

    @Autowired
    private ClothesRepo clothesRepo;

    public Page<Clothes> getFilteredClothes(
        String search, List<String> categories, List<String> sizes, String location, Float minPrice, Float maxPrice,
        String orderBy, Pageable pageable
    ) {

        // Define Sort
        Sort sort = switch (orderBy) {
            case "price_asc" -> Sort.by(Sort.Direction.ASC, "price");
            case "price_desc" -> Sort.by(Sort.Direction.DESC, "price");
            case "newest" -> Sort.by(Sort.Direction.DESC, "postDate");
            case "oldest" -> Sort.by(Sort.Direction.ASC, "postDate");
            case "popular" -> Sort.by(Sort.Direction.DESC, "favoritesCount");
            case "less_popular" -> Sort.by(Sort.Direction.ASC, "favoritesCount");
            default -> Sort.unsorted();
        };

        // Creating Sorted Pageable
        Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

        // Filter and Order
        return clothesRepo.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (search != null && !search.isEmpty()) {
                // Concatenate brand + " " + model as Expressions
                Expression<String> concatenatedBrandModel = cb.concat(
                    cb.concat(cb.lower(root.get("brand")),
                    cb.literal(" ")),
                    cb.lower(root.get("model"))
                );

                // Create a Predicate to check if the concatenated brand and model contains the search term
                Predicate concatenatedMatch = cb.like(concatenatedBrandModel, "%" + search.toLowerCase() + "%");

                // Add the predicate to the list of filters
                predicates.add(concatenatedMatch);
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
        }, sortedPageable);
    }

    public Clothes updateClothe(int id, ClotheDTO dto) {
        Clothes existingClothe = clothesRepo.findById((long) id)
                .orElseThrow(() -> new EntityNotFoundException("Clothe not found"));

        existingClothe.setBrand(dto.getBrand());
        existingClothe.setModel(dto.getModel());
        existingClothe.setCategory(dto.getCategory());
        existingClothe.setPrice(dto.getPrice());
        existingClothe.setPublisher(dto.getPublisher());
        existingClothe.setPicture(dto.getPicture());
        existingClothe.setSize(dto.getSize());
        existingClothe.setState(dto.getState());
        existingClothe.setLocation(dto.getLocation());
        existingClothe.setSaleState(dto.getSaleState());

        return clothesRepo.save(existingClothe);
    }
}
