package com.gestion.repositories;

import java.util.*;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.gestion.models.Product;

public interface I_ProductRepository extends MongoRepository<Product, String>{
	
}
