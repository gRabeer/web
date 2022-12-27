package com.example.demo.services;

import com.example.demo.models.Image;
import com.example.demo.models.Product;
import com.example.demo.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> listProducts(String title){
        if (title != null) productRepository.findByTitle(title);
        return productRepository.findAll();
    }

    public void saveProduct(Product product, MultipartFile file1
    ) throws IOException {
        Image image1;

        if (file1.getSize() != 0) {
            image1 = toImageEntity(file1);
            image1.setPreviewImage(true);
            product.addImageToProduct(image1);
        }

        log.info("Saving new Product. Title: {}; Author: {}", product.getTitle(), product.getAuthor());
        Product productFromDb = productRepository.save(product);
        productFromDb.setPreviewImageId(productFromDb.getImages().get(0).getId());
        productRepository.save(product);
    }

    //Преобразование файла в модель фотографии
    private Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }



    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }
    public Product getProductById(Long id){

        return productRepository.findById(id).orElse(null);
    }


    public void editProduct(Product product, MultipartFile file1, Long id) throws IOException {

        productRepository.deleteById(id);

        Image image1;
        Image image2;

        if (file1.getSize() != 0) {
            image1 = toImageEntity(file1);
            image1.setPreviewImage(true);
            product.addImageToProduct(image1);
        }

      //  if (file2.getSize() != 0) {
       //     image2 = toImageEntity(file2);
      //      product.addImageToProduct(image2);
      //  }


        log.info("Saving new Product. Title: {}; Author: {}", product.getTitle(), product.getAuthor());
        Product productFromDb = productRepository.save(product);
        productFromDb.setPreviewImageId(productFromDb.getImages().get(0).getId());
        productRepository.save(product);
    }


    public void editProduct2(Product product, MultipartFile file1,
                             Long id) throws IOException {


        Product editproduct=getProductById(id);
        System.out.println(editproduct.getImages().get(0).getId());
        System.out.println(editproduct.getImages().get(1).getId());

        Image image1;
        Image image2;

        editproduct.setImages(editproduct.getImages());
        editproduct.setTitle(product.getTitle());
        editproduct.setPrice(product.getPrice());
        editproduct.setAuthor(product.getAuthor());
        editproduct.setCity(product.getCity());
        editproduct.setDescription(product.getDescription());

        log.info("edit new Product. Title: {}; Author: {}", product.getTitle(), product.getAuthor());
        Product productFromDb = productRepository.save(editproduct);
        productFromDb.setPreviewImageId(productFromDb.getImages().get(0).getId());
        productRepository.save(editproduct);
    }
}
