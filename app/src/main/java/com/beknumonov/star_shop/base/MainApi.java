package com.beknumonov.star_shop.base;

import com.beknumonov.star_shop.model.Banner;
import com.beknumonov.star_shop.model.Cart;
import com.beknumonov.star_shop.model.CartRequest;
import com.beknumonov.star_shop.model.CartResponse;
import com.beknumonov.star_shop.model.Category;
import com.beknumonov.star_shop.model.Classification;
import com.beknumonov.star_shop.model.Order;
import com.beknumonov.star_shop.model.OrderRequest;
import com.beknumonov.star_shop.model.Product;
import com.beknumonov.star_shop.model.SubProduct;
import com.beknumonov.star_shop.model.User;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MainApi {


    @POST("/v1/login/")
    Call<User> login(@Body User user);

    @POST("/v1/user/")
    Call<User> createUser(@Body User user);


    @GET("/v1/banner/")
    Call<ArrayList<Banner>> getBanners();



    /*Products*/

    @GET("/v1/classification/")
    Call<ArrayList<Classification>> getClassifications();

    @GET("/v1/classification/{id}/category/")
    Call<ArrayList<Category>> getCategories(@Path("id") int classificationId);


    @GET("/v1/category/{id}/subproduct/")
    Call<ArrayList<SubProduct>> getSubProducts(@Path("id") int categoryId);


    // Products


    @GET("/v1/subproduct/{id}/products/")
    Call<ArrayList<Product>> getProductsBySubProduct(@Path("id") int subProductId);

    @GET("/v1/product/{id}/")
    Call<Product> getProductDetails(@Path("id") int productId);


    // Cart

    @POST("/v1/cart/")
    Call<CartResponse> addProductToCart(@Body CartRequest cartRequest);

    @GET("/v1/cart/")
    Call<ArrayList<Cart>> getMyCart();

    @POST("/v1/cart/order/")
    Call<Order> orderCarts(@Body OrderRequest orderRequest);


}
