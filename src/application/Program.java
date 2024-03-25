package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import Entities.Sale;

public class Program {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        List<Sale> list = new ArrayList<>();

        System.out.print("Enter file full path: ");
        String path = sc.nextLine();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine();
            while (line != null) {
                String[] fields = line.split(",");
                Integer month = Integer.parseInt(fields[0]);
                Integer year = Integer.parseInt(fields[1]);
                String seller = fields[2];
                Integer items = Integer.parseInt(fields[3]);
                Double total = Double.parseDouble(fields[4]);

                list.add(new Sale(month, year, seller, items, total));
                line = br.readLine();
            }

            Map<String, Double> salesBySeller = new HashMap<>();
            for (Sale sale : list) {
                String seller = sale.getSeller();
                Double total = sale.getTotal();
                salesBySeller.put(seller, salesBySeller.getOrDefault(seller, 0.0) + total);
            }

            for (String seller : salesBySeller.keySet()) {
                System.out.println(seller + " - R$ " + salesBySeller.get(seller));
            }

        } catch (IOException e) {
            System.out.println("O sistema não pode encontrar o arquivo especificado " + e.getMessage());

        } finally {
            if (sc != null) {
                sc.close();
            }
        }
    }
}
