package com.example.demo.handler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.example.demo.model.Book;

public class BookHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent>{
	List<Book> books = Arrays.asList(
	        new Book("Tirukural", "Valluvar",650, 3.4),
	        new Book("Ramayanam", "Valmiki", 850,4.3),
	        new Book("Koo Koo", "Carl",350, 4.8),
	        new Book("Little stories", "dark man",250, 4.1),
	        new Book("Atomic habits", "Jammes",500, 4.9),
	        new Book("Java", "Ben",700, 3.4),
	        new Book("Python", "Donald", 400,4.6)
	    );
	@Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {

        try {
            // input-> api-gateway-url?rating=4.5
            String inputRating = input.getQueryStringParameters().get("rating");
            double d = Double.valueOf(inputRating);

            List<Book> fList = new ArrayList<Book>();
            for(Book m:books){
                if(m.getRating()>d){
                    fList.add(m);
                }
            }

            return new APIGatewayProxyResponseEvent()
            .withBody(fList.toString())
            .withStatusCode(200);

        } catch (Exception e) {
            return new APIGatewayProxyResponseEvent()
                    .withBody(books.toString())
                    .withStatusCode(200);
        }
    }
}
