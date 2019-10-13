package dev.ulman.restaurant.api;

import org.apache.commons.cli.*;

import java.math.BigDecimal;

public class CLParser {

    private Options options;
    private CommandLineParser parser;
    private HelpFormatter helpFormatter;
    private ApiUtils apiUtils;

    public CLParser() {
        this.options = new Options();
        this.parser = new DefaultParser();
        this.helpFormatter = new HelpFormatter();
        this.apiUtils = new ApiUtilsImpl();

        //help
        options.addOption("h", "help", false, "Show help");
        //show menu
        options.addOption("m", "menu", false, "Shows menu");
        // get bill
        options.addOption("b", "bill", false, "Get order sum and delete order");
        //order lunch
        Option lunch = Option.builder("l")
                .longOpt("lunch")
                .numberOfArgs(2)
                .argName("main_course> <dessert")
                .desc("Order lunch consists of the main course and dessert")
                .build();
        options.addOption(lunch);
        //order drink
        Option drink = Option.builder("d")
                .longOpt("drink")
                .numberOfArgs(2)
                .argName("drink> <addition_to_drink")
                .optionalArg(true)
                .desc("Order drink with addition")
                .build();
        options.addOption(drink);

        //add cuisine
        Option addCuisine = Option.builder("ac")
                .longOpt("addCuisine")
                .numberOfArgs(1)
                .argName("Cuisine_name")
                .desc("Add new cuisine")
                .build();
        options.addOption(addCuisine);
        //add product
        Option addProduct = Option.builder("ap")
                .longOpt("addProduct")
                .numberOfArgs(3)
                .argName("product_name> <price> <cuisine_name")
                .desc("Add new product")
                .build();
        options.addOption(addProduct);
    }

    public void argsParser(String[] args) {
        try {
            CommandLine commandLine = this.parser.parse(this.options, args);
            if (commandLine.hasOption("help")) {
                helpFormatter.printHelp("Command Line Parameters", options);
            } else if (commandLine.hasOption("menu")) {
                apiUtils.showMenu();
            } else if (commandLine.hasOption("bill")) {
                apiUtils.paidBill();
            } else if (commandLine.hasOption("lunch") && commandLine.hasOption("drink")) {
                String[] lunchValues = commandLine.getOptionValues("lunch");
                apiUtils.orderLunch(lunchValues[0], lunchValues[1]);
                String[] drinkValues = commandLine.getOptionValues("drink");
                if (drinkValues.length > 1)
                    apiUtils.orderDrink(drinkValues[0], drinkValues[1]);
                else
                    apiUtils.orderDrink(drinkValues[0]);
            } else if (commandLine.hasOption("lunch")) {
                String[] values = commandLine.getOptionValues("lunch");
                apiUtils.orderLunch(values[0], values[1]);
            } else if (commandLine.hasOption("drink")) {
                String[] values = commandLine.getOptionValues("drink");
                if (values.length > 1)
                    apiUtils.orderDrink(values[0], values[1]);
                else
                    apiUtils.orderDrink(values[0]);
            } else if (commandLine.hasOption("addCuisine")) {
                String value = commandLine.getOptionValue("addCuisine");
                apiUtils.addNewCuisine(value);
            } else if (commandLine.hasOption("addProduct")) {
                String[] values = commandLine.getOptionValues("addProduct");
                BigDecimal price;
                try {
                    price = new BigDecimal(values[1]);
                    apiUtils.addNewProduct(values[0], price, values[2]);
                } catch (NumberFormatException e) {
                    System.out.println(values[1] + " this is not a number. Try like this: 15.33");
                }
            } else System.out.println("This is not recognized option. Try --help");

        } catch (ParseException e) {
            e.printStackTrace();
        }

    }


}
