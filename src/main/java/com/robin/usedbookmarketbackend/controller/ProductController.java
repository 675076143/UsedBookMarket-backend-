package com.robin.usedbookmarketbackend.controller;

import com.robin.reactmarket.config.ProductResponse;
import com.robin.reactmarket.config.Response;
import com.robin.reactmarket.dto.product.DtoUpdateProducts;
import com.robin.reactmarket.dto.product.DtoUpdateProductsStatus;
import com.robin.reactmarket.model.Product;
import com.robin.reactmarket.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public Response getProducts(int pageNum, int pageSize){
        int totalPage;  //总页数
        int index = pageSize*(pageNum-1);      //从第几条数据开始(0是第一条)
        int num = pageSize;
        int totalNum = productService.countProducts("%%","%%");  //获取总条数
        totalPage = totalNum/pageSize;  //总页数=总条数/每页条数
        List<Product> products = productService.getProducts(index,num);  //根据起始位置和数目来获取数据
        ProductResponse productResponse = new ProductResponse(pageNum,pageSize,totalNum,totalPage,products);
        return new Response("200","success",productResponse);
    }

    @GetMapping("/products/productName")
    public Response getProductsByName(String productName, int pageNum, int pageSize){
        int totalPage;  //总页数
        int index = pageSize*(pageNum-1);      //从第几条数据开始(0是第一条)
        int num = pageSize;
        int totalNum = productService.countProducts("%"+productName+"%","%%");  //获取总条数
        totalPage = totalNum/pageSize;  //总页数=总条数/每页条数
        List<Product> products = productService.getProductsByName("%"+productName+"%",index,num);  //根据起始位置和数目来获取数据
        ProductResponse productResponse = new ProductResponse(pageNum,pageSize,totalNum,totalPage,products);
        return new Response("200","success",productResponse);
    }

    @GetMapping("/products/productDesc")
    public Response getProductsByDesc(String productDesc, int pageNum, int pageSize){
        int totalPage;  //总页数
        int index = pageSize*(pageNum-1);      //从第几条数据开始(0是第一条)
        int num = pageSize;
        int totalNum = productService.countProducts("%%","%"+productDesc+"%");  //获取总条数
        totalPage = totalNum/pageSize;  //总页数=总条数/每页条数
        List<Product> products = productService.getProductsDesc("%"+productDesc+"%",index,num);  //根据起始位置和数目来获取数据
        ProductResponse productResponse = new ProductResponse(pageNum,pageSize,totalNum,totalPage,products);
        return new Response("200","success",productResponse);
    }

    @PostMapping("/products")
    public Response addProducts(String productName, String productDesc,
                                double price, int secondaryCategoryID,
                                String detail, int status, String image){
        try {
            productService.addProduct(productName,productDesc,price,secondaryCategoryID,detail,status,image);
            return new Response("200","success",null);
        }catch (Exception e){
            return new Response("400","failed",e.toString());
        }

    }

    @PutMapping("/products/{productID}")
    public Response updateProducts(@PathVariable("productID") int productID,
                                   @RequestBody @Validated DtoUpdateProducts data){
        try {
            String productName = data.getProductName();
            String productDesc = data.getProductDesc();
            double price = data.getPrice();
            int secondaryCategoryID = data.getSecondaryCategoryID();
            String detail = data.getDetail();
            int status = data.getStatus();
            String image=data.getImage();
            productService.updateProduct(productName,productDesc,price,secondaryCategoryID,detail,status,image,productID);
            return new Response("200","success",null);
        }catch (Exception e){
            return new Response("400","failed",e.toString());
        }
    }

    @PutMapping("/productStatus/{productID}")
    public Response updateProductsStatus(@PathVariable("productID") int productID,
                                         @RequestBody @Validated DtoUpdateProductsStatus data){
        try {
            int status = data.getStatus();
            productService.updateProductStatus(productID,status);
            return new Response("200","success",null);
        }catch (Exception e){
            return new Response("400","failed",e.toString());
        }
    }

    @DeleteMapping("/products/{productID}")
    public Response deleteProducts(@PathVariable("productID") int productID){
        try {
            productService.deleteProduct(productID);
            return new Response("200","success",null);
        }catch (Exception e){
            return new Response("400","failed",null);
        }
    }

}
