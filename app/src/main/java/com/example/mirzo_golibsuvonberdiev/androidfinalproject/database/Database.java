package com.example.mirzo_golibsuvonberdiev.androidfinalproject.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.example.mirzo_golibsuvonberdiev.androidfinalproject.models.Food;
import com.example.mirzo_golibsuvonberdiev.androidfinalproject.models.Order;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mirzo-golibsuvonberdiev on 12/19/18.
 */

public class Database extends SQLiteAssetHelper {
    private static final String DB_NAME = "OrderDetail.db";
    private static final int DB_VER = 1;


    public Database(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }


    public List<Order> getOrders() {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        String[] sqlSelect = {"productId", "productName", "quantity", "price"};
        String sqlTable = "OrderDetail";

        queryBuilder.setTables(sqlTable);

        Cursor c = queryBuilder.query(db, sqlSelect, null, null, null, null, null);

        final List<Order> result = new ArrayList<>();
        if (c.moveToFirst()){
            do {
                result.add(new Order(c.getString(c.getColumnIndex("productId")),
                        c.getString(c.getColumnIndex("productName")),
                        c.getString(c.getColumnIndex("quantity")),
                        c.getString(c.getColumnIndex("price"))
                        ));

            }while (c.moveToNext());

        }


        return result;
    }

    public void addOrders(Order order){
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("INSERT INTO OrderDetail (productId, productName, quantity, price) VALUES('%s','%s','%s','%s')",
                order.getPrductId(), order.getProductName(), order.getQuantity(), order.getPrice());
        db.execSQL(query);
    }

    public void delete(){
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("DELETE FROM OrderDetail");
        db.execSQL(query);
    }


    //Favourite
    public void addToFavourites(String foodId){
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("INSERT INTO Favourites (foodId) VALUES ('%s');", foodId);
        db.execSQL(query);
    }

    public void addToFavouritesWithObject(Food food){
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("INSERT INTO Favourites (foodId, name, image, price, description) VALUES ('%s','%s','%s','%s','%s');",
                food.getFoodId(), food.getName(), food.getImage(), food.getPrice(), food.getDescription());
        db.execSQL(query);
    }

    public void deleteFromFavourites(String foodId){
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("DELETE FROM Favourites WHERE foodId='%s';", foodId);
        db.execSQL(query);
    }


    public boolean isFavourites(String foodId){
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("SELECT * FROM Favourites WHERE foodId='%s';", foodId);
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount()<=0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }



    public List<Food> getFavourite() {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        String[] sqlSelect = {"foodId", "name", "image", "price", "description"};
        String sqlTable = "Favourites";

        queryBuilder.setTables(sqlTable);

        Cursor c = queryBuilder.query(db, sqlSelect, null, null, null, null, null);

        final List<Food> result = new ArrayList<>();
        if (c.moveToFirst()){
            do {
                result.add(new Food(c.getString(c.getColumnIndex("foodId")),
                        c.getString(c.getColumnIndex("name")),
                        c.getString(c.getColumnIndex("image")),
                        c.getString(c.getColumnIndex("price")),
                        c.getString(c.getColumnIndex("description"))
                ));

            }while (c.moveToNext());

        }


        return result;
    }

}
