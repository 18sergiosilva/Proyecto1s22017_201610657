package com.example.andriod.edd;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by elizabeth on 28/09/2017.
 */

public interface RegisterAPI {
    @FormUrlEncoded
    @POST("/validarUser")
    public void validarUser(
        @Field("user") String user,
        @Field("password") String password,
        Callback<Response> callback);

    @FormUrlEncoded
    @POST("/insertarUser")
    public void insertarUser(
            @Field("user") String user,
            @Field("password") String password,
            Callback<Response> callback);

    @FormUrlEncoded
    @POST("/crearCarpetaAndroid")
    public void crearCarpetaAndroid(
            @Field("directorio") String directorio,
            @Field("directorio2") String directorio2,
            Callback<Response> callback);

    @FormUrlEncoded
    @POST("/crearCarpetaAndroidInicial")
    public void crearCarpetaAndroidInicial(
            @Field("directorio") String directorio,
            Callback<Response> callback);


    @FormUrlEncoded
    @POST("/subirArchivoMiCarpeta")
    public void subirArchivoMiCarpeta(
            @Field("nombreCarpeta") String nombreCarpeta,
            @Field("nombreArchivo") String nombreArchivo,
            Callback<Response> callback);

    @FormUrlEncoded
    @POST("/subirArchivoACarpeta")
    public void subirArchivoACarpeta(
            @Field("carpetaUsuario") String carpetaUsuario,
            @Field("nombreCarpeta") String nombreCarpeta,
            @Field("nombreArchivo") String nombreArchivo,
            Callback<Response> callback);

    @FormUrlEncoded
    @POST("/subirArchivo")
    public void subirArchivo(
            @Field("archivo") String archivo,
            @Field("user") String user,
            Callback<Response> callback);

    @FormUrlEncoded
    @POST("/descargarArchivo")
    public void descargarArchivo(
            @Field("archivo") String archivo,
            @Field("key") String key,
            @Field("user") String user,
            Callback<Response> callback);

}
