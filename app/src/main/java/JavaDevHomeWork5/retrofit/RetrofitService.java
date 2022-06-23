package JavaDevHomeWork5.retrofit;

import JavaDevHomeWork5.responce.model.ApiResponse;
import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public final class RetrofitService {
    private static final String baseURL = "https://petstore.swagger.io/v2/";

    public static <T> T getRetrofitService (Class<T> serviceClass){
       return new Retrofit.Builder()
               .client(new OkHttpClient())
               .baseUrl(baseURL)
               .addConverterFactory(GsonConverterFactory.create())
               .build()
               .create(serviceClass);
    }

    public static  <T>T cheekResponseCall (Call<T> responseCall) throws IOException {
        Response<T> execute = responseCall.execute();
        if(execute.isSuccessful()){
            System.out.println("You request is successful!");
            return execute.body();
        }else {
            ApiResponse apiResponse = parseErrBodyToApiResponse(execute);
            String errorMassage = "You request is failed:\n"
                    + apiResponse.getCode()
                    + " -> "
                    + apiResponse.getMessage();
            throw new IOException(errorMassage);
        }
    }

    private static <T> ApiResponse parseErrBodyToApiResponse(Response<T> execute){
        try {
            Gson gson = new Gson();
            return gson.fromJson(execute.errorBody().string().replace("\"", "'"), ApiResponse.class);
        }catch (RuntimeException | IOException e){
            return new ApiResponse(execute.code(),"","Unknown error");
        }
    }

}
