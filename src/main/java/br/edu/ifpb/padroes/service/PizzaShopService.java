package br.edu.ifpb.padroes.service;

import br.edu.ifpb.padroes.api.damenos.DamenosPizza;
import br.edu.ifpb.padroes.api.damenos.DamenosServiceImpl;
import br.edu.ifpb.padroes.api.damenos.proxy.DamenosService;
import br.edu.ifpb.padroes.api.damenos.proxy.DamenosServiceProxy;
import br.edu.ifpb.padroes.api.pizzahot.PizzaHotPizza;
import br.edu.ifpb.padroes.api.pizzahot.PizzaHotServiceImpl;
import br.edu.ifpb.padroes.api.pizzahot.proxy.PizzaHotService;
import br.edu.ifpb.padroes.api.pizzahot.proxy.PizzaHotServiceProxy;
import br.edu.ifpb.padroes.domain.Pizza;
import br.edu.ifpb.padroes.domain.adapter.DamenosAdapter;
import br.edu.ifpb.padroes.domain.adapter.PizzahotAdapter;

import java.util.ArrayList;
import java.util.List;

public class PizzaShopService {

    private DamenosService damenosService;
    private PizzaHotService pizzaHotService;

    public PizzaShopService() {
        damenosService = new DamenosServiceProxy();
        pizzaHotService = new PizzaHotServiceProxy();
    }

    // TODO - implementar decorator para não precisar atributos da pizza como parâmetros no método
    public void orderPizza(Pizza pizza) {

        Float totalPrice = pizza.getPrice();
        String name = pizza.getName();

        System.out.println(String.format("New order for = %s", name));
        System.out.println(String.format("Total price = %f", totalPrice));

    }

    // TODO - implementar adapter para unificar pizzas vindas das APIs Damenos e PizzaHot num único método getPizzas()
    public List<Pizza> getPizzas() {
        List<Pizza> listaPizzas = new ArrayList<>();
//        List<DamenosPizza> damenosPizzasList = damenosService.getPizzas();
//        List<PizzaHotPizza> pizzaHotPizzaList = pizzaHotService.getPizzas();
//
//        for (DamenosPizza damenoPizza: damenosPizzasList ) {
//            listaPizzas.add(new DamenosAdapter(damenoPizza));
//        }
//        for (PizzaHotPizza pizzaHotPizza: pizzaHotPizzaList ) {
//            listaPizzas.add(new PizzahotAdapter(pizzaHotPizza));
//        }
        damenosService.getPizzas().forEach(damenosPizza -> {listaPizzas.add(new DamenosAdapter(damenosPizza));});

        pizzaHotService.getPizzas().forEach(pizzaHotPizza -> {listaPizzas.add(new PizzahotAdapter(pizzaHotPizza));});

        return listaPizzas;
    }

}
