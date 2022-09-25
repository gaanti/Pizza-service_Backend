package db.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class V1_2_1__add_pizza_with_java extends BaseJavaMigration {
	public void migrate(final Context context) throws Exception {
		Connection connection = context.getConnection();
		String insertPizzas = "INSERT INTO pizzas (title, image, price, popularity) VALUES (?, ?, ?, ?)";
		PreparedStatement preparedStatement = connection.prepareStatement(insertPizzas);

		// Migration #1
		preparedStatement.setString(1, "Cheeseburger Pizza");
		InputStream image = new ClassPathResource("pizzas/Cheeseburger_Pizza.jpeg").getInputStream();
		preparedStatement.setBlob(2, image);
		preparedStatement.setFloat(3, 29.99f);
		preparedStatement.setInt(4, 4);
		preparedStatement.execute();

		// Migration #2
		preparedStatement.setString(1, "Cheesy Chicken");
		image = new ClassPathResource("pizzas/Cheesy_Chicken.jpg").getInputStream();
		preparedStatement.setBlob(2, image);
		preparedStatement.setFloat(3, 34.99f);
		preparedStatement.setInt(4, 6);
		preparedStatement.execute();

		// Migration #3
		preparedStatement.setString(1, "Chicken BBQ");
		image = new ClassPathResource("pizzas/Chicken_BBQ.jpg").getInputStream();
		preparedStatement.setBlob(2, image);
		preparedStatement.setFloat(3, 29.99f);
		preparedStatement.setInt(4, 4);
		preparedStatement.execute();

		// Migration #4
		preparedStatement.setString(1, "Margherita");
		image = new ClassPathResource("pizzas/Margherita.jpg").getInputStream();
		preparedStatement.setBlob(2, image);
		preparedStatement.setFloat(3, 31.99f);
		preparedStatement.setInt(4, 8);
		preparedStatement.execute();

		// Migration #5
		preparedStatement.setString(1, "Meaty BBQ");
		image = new ClassPathResource("pizzas/Meaty_BBQ.jpg").getInputStream();
		preparedStatement.setBlob(2, image);
		preparedStatement.setFloat(3, 24.99f);
		preparedStatement.setInt(4, 3);
		preparedStatement.execute();

		// Migration #6
		preparedStatement.setString(1, "Pepperoni");
		image = new ClassPathResource("pizzas/Pepperoni.jpg").getInputStream();
		preparedStatement.setBlob(2, image);
		preparedStatement.setFloat(3, 24.99f);
		preparedStatement.setInt(4, 2);
		preparedStatement.execute();

		// Migration #7
		preparedStatement.setString(1, "Super Meaty");
		image = new ClassPathResource("pizzas/Super_Meaty.jpg").getInputStream();
		preparedStatement.setBlob(2, image);
		preparedStatement.setFloat(3,  33.99f);
		preparedStatement.setInt(4, 5);
		preparedStatement.execute();

		// Migration #8
		preparedStatement.setString(1, "Veggie Overload");
		image = new ClassPathResource("pizzas/Veggie-Overload.jpg").getInputStream();
		preparedStatement.setBlob(2, image);
		preparedStatement.setFloat(3, 55.99f);
		preparedStatement.setInt(4, 10);
		preparedStatement.execute();

		// Migration #9
		preparedStatement.setString(1, "Hawaiian");
		image = new ClassPathResource("pizzas/Hawaiian.jpg").getInputStream();
		preparedStatement.setBlob(2, image);
		preparedStatement.setFloat(3, 44.99f);
		preparedStatement.setInt(4, 8);
		preparedStatement.execute();

		// Migration #10
		preparedStatement.setString(1, "Beef Suya");
		image = new ClassPathResource("pizzas/Beef-Suya.jpg").getInputStream();
		preparedStatement.setBlob(2, image);
		preparedStatement.setFloat(3, 34.99f);
		preparedStatement.setInt(4, 7);
		preparedStatement.execute();

		// Migration #11
		preparedStatement.setString(1, "Sweet Chili Chicken");
		image = new ClassPathResource("pizzas/Sweet-Chili-Chicken.jpg").getInputStream();
		preparedStatement.setBlob(2, image);
		preparedStatement.setFloat(3, 43.99f);
		preparedStatement.setInt(4, 5);
		preparedStatement.execute();

		// Migration #12
		preparedStatement.setString(1, "Spicy Mixed Pizza");
		image = new ClassPathResource("pizzas/Spicy_Mixed_Pizza.jpg").getInputStream();
		preparedStatement.setBlob(2, image);
		preparedStatement.setFloat(3, 33.99f);
		preparedStatement.setInt(4, 5);
		preparedStatement.execute();

	}
}