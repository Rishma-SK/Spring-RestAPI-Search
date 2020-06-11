package io.demo.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import io.demo.model.Product;

/**
 * 
 * @author Rishma Sankarbabu
 *
 */
public class CSVParser {

	private static final Logger LOG = LoggerFactory.getLogger(CSVParser.class);
	
	/**
	 * 
	 * @param csvFile
	 * @return
	 * @throws IOException
	 */
	public static List<Product> loadProducts(String csvFile) throws IOException {

		List<Product> productList = new ArrayList<Product>();

		try (CSVReader reader = new CSVReaderBuilder(new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream(csvFile))).withSkipLines(1).build();) {
			
			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				
				Product product = new Product();
				product.setType(nextLine[0]);
				product.setProperties(nextLine[1]);
				product.setPrice(Float.parseFloat(nextLine[2]));
				product.setStore_address(nextLine[3]);
				productList.add(product);
			}
		}
		catch(Exception ex) {
			LOG.error("Exception found "+ex);
		}

		return productList;
	}

}
