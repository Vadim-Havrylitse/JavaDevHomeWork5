package JavaDevHomeWork5.retrofit;

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
            String errorMassage = execute.code()
                    + " -> "
                    + execute.errorBody().string();
            System.err.println("You request is failed:\n"
                    + errorMassage);
            throw new RuntimeException(errorMassage);
        }
    }

}
