package com.example.themasterchefapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FoodAdapter
    private lateinit var allFoodItems: List<FoodItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = layoutManager

        allFoodItems = createFoodList()
        adapter = FoodAdapter(allFoodItems)
        recyclerView.adapter = adapter

        setupSearchView()
        setupCategoryIcons()
    }

    private fun createFoodList(): List<FoodItem> {
        return listOf(
            FoodItem(R.drawable.breakfast1, "Pancakes", "Fluffy stack of pancakes with syrup.", "Breakfast"),
            FoodItem(R.drawable.breakfast2, "Full Breakfast", "Complete breakfast with eggs, bacon, sausage, and toast.", "Breakfast"),
            FoodItem(R.drawable.breakfast3, "Waffles", "Waffles with berries and syrup on top.", "Breakfast"),
            FoodItem(R.drawable.breakfast4, "Parfait", "Yogurt parfait with granola and fruit layers.", "Breakfast"),
            FoodItem(R.drawable.dessert1, "Sundae", "Ice cream sundae with whipped cream and a cherry on top.", "Dessert"),
            FoodItem(R.drawable.dessert2, "Chocolate Cake", "Rich, dark chocolate cake with frosting.", "Dessert"),
            FoodItem(R.drawable.drink1, "Root Beer", "A can of root beer soda.", "Drink"),
            FoodItem(R.drawable.drink2, "Coca-Cola", "Classic Coca-Cola can.", "Drink"),
            FoodItem(R.drawable.drink3, "Green Juice", "Glass of green apple juice.", "Drink"),
            FoodItem(R.drawable.drink4, "Orange Juice", "Freshly squeezed orange juice in a glass.", "Drink"),
            FoodItem(R.drawable.main1, "Spaghetti with Meatballs", "Pasta served with meatballs and tomato sauce.", "Main Course"),
            FoodItem(R.drawable.main2, "Cheeseburgers", "Juicy cheeseburgers with fries on the side.", "Main Course"),
            FoodItem(R.drawable.main3, "Pizza", "Cheese and pepperoni pizza with a crispy crust.", "Main Course"),
            FoodItem(R.drawable.main4, "BBQ Ribs", "Barbecued ribs served with a side of fries.", "Main Course"),
            FoodItem(R.drawable.pizza1, "Pizza with Vegetables", "Pizza topped with mushrooms, peppers, and onions.", "Pizza"),
            FoodItem(R.drawable.pizza2, "Pepperoni Pizza", "Classic pepperoni pizza with a golden crust.", "Pizza"),
            FoodItem(R.drawable.pizza3, "Margherita Pizza", "Margherita pizza topped with fresh basil and mozzarella.", "Pizza"),
            FoodItem(R.drawable.pizza4, "Mixed Pizza", "Pizza with assorted toppings like pepperoni, olives, and mushrooms.", "Pizza"),
            FoodItem(R.drawable.seafood1, "Oysters", "Fresh oysters served on a plate with lemon wedges.", "Seafood"),
            FoodItem(R.drawable.seafood2, "Shrimp", "Cooked shrimp garnished with herbs and lemon.", "Seafood"),
            FoodItem(R.drawable.seafood3, "Grilled Lobster", "Grilled lobster with butter and herbs.", "Seafood"),
            FoodItem(R.drawable.seafood4, "Sliced Salmon", "Freshly sliced raw salmon ready to be served.", "Seafood"),
            FoodItem(R.drawable.snack1, "Popcorn", "Buttered popcorn in a striped tub.", "Snack"),
            FoodItem(R.drawable.snack2, "Pocky Sticks", "Chocolate-dipped biscuit sticks.", "Snack"),
            FoodItem(R.drawable.snack3, "Chocolate Bar", "A block of rich milk chocolate.", "Snack"),
            FoodItem(R.drawable.snack4, "Lay's Chips", "Bag of Lay's strong-flavored potato chips.", "Snack"),
            FoodItem(R.drawable.soup1, "Cream Soup", "Bowl of creamy mushroom soup with herbs.", "Soup"),
            FoodItem(R.drawable.soup2, "Vegetable Soup", "A bowl of chunky vegetable soup with broth.", "Soup"),
            FoodItem(R.drawable.soup3, "Potato Soup", "Thick potato soup with green onions and bacon bits.", "Soup"),
            FoodItem(R.drawable.soup4, "Chicken Noodle Soup", "Comforting chicken noodle soup with carrots and herbs.", "Soup"),
            FoodItem(R.drawable.starter1, "Fried Shrimp", "Fried shrimp served with dipping sauce.", "Starter"),
            FoodItem(R.drawable.starter2, "Chicken Skewers", "Grilled chicken skewers with veggies.", "Starter"),
            FoodItem(R.drawable.starter3, "Grilled Vegetables", "Assorted grilled vegetables like bell peppers and zucchini.", "Starter"),
            FoodItem(R.drawable.starter4, "Stuffed Mushrooms", "Mushrooms stuffed with a savory filling of cheese and herbs.", "Starter")
        )
    }

    private fun setupSearchView() {
        val searchView = findViewById<SearchView>(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val filteredList = allFoodItems.filter {
                    it.title.contains(newText ?: "", ignoreCase = true) ||
                            it.description.contains(newText ?: "", ignoreCase = true)
                }
                adapter.updateList(filteredList)
                return true
            }
        })
    }

    private fun setupCategoryIcons() {
        val categoryIcons = mapOf(
            R.id.starter to "Starter",
            R.id.snack to "Snack",
            R.id.dessert to "Dessert",
            R.id.pizza to "Pizza",
            R.id.seafood to "Seafood",
            R.id.maincourse to "Main Course",
            R.id.drink to "Drink",
            R.id.soup to "Soup"
        )

        categoryIcons.forEach { (iconId, category) ->
            findViewById<ImageView>(iconId).setOnClickListener {
                val filteredList = allFoodItems.filter { it.category == category }
                adapter.updateList(filteredList)
            }
        }
    }
}
