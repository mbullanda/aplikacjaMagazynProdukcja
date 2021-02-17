package pl.michal.inTransport;

import pl.michal.user.User;
import pl.michal.warehouse.WarehouseProducts;

public class InTransportTab {
//    private InTransport[] inTransportTab = new InTransport[100];
//    private InTransportTab[] inTransportHistory = new InTransportTab[100];
//    private boolean isAvailable = true;
//    private String loginOfUser;
//    private double weightOfAllProducts;
//
//    public void addToTab (InTransport inTransport){
//        for (int i = 0; i < inTransportTab.length; i++) {
//            if (inTransportTab[i] == null) {
//                inTransportTab[i] = inTransport;
//                break;
//            }
//        }
//    }
//    public void clearTab() {
//        for (int i = 0; i < inTransportTab.length; i++) {
//            if (inTransportTab[i] != null) {
//                inTransportTab[i] = null;
//            }
//        }
//    }
//
//    public void addToHistory (InTransportTab inTransportTab){
//        for (int i = 0; i < inTransportHistory.length; i++) {
//            if (inTransportHistory[i] == null) {
//                inTransportHistory[i] = inTransportTab;
//                break;
//            }
//        }
//    }
//
//    public void displayHistory() {
//        System.out.println("Historia wydań towaru:");
//        for (int i = 0; i < inTransportHistory.length; i++) {
//            if (inTransportHistory[i] != null) {
//                InTransportTab inTransportTab = inTransportHistory[i];
//                System.out.print("Transport nr " + (i + 1) + ". Łączna waga: " + inTransportTab.getWeightOfAllProducts()
//                        + "kg. Użytkownik: " + inTransportTab.getLoginOfUser());
//                if (inTransportTab.isAvailable) {
//                    System.out.println("--> Status: odebrana na magazynie głównym.");
//                } else {
//                    System.out.println("--> Status: w transporcie.");
//                }
//            }
//        }
//
//    }
//
//    public void displayListOfProductsInTransport() {
//        System.out.println("Transport produktu na magazyn główny. Użytkownik: " + this.loginOfUser);
//        for (int i = 0; i < inTransportTab.length; i++) {
//            if (inTransportTab[i] != null) {
//                InTransport inTransport = inTransportTab[i];
//                System.out.println(i + 1 + ". " + inTransport.getName() + " " + inTransport.getColor() +
//                        ": " + inTransport.getQuantityInTransport() + " sztuk. Łączna waga: " +
//                        (inTransport.getWeight() * inTransport.getQuantityInTransport()));
//            }
//        }
//    }
//
//    public void setAvailable(boolean available) {
//        isAvailable = available;
//    }
//    public boolean getAvailable() {
//        return isAvailable;
//    }
//
//    public void setLoginOfUser(String loginOfUser) {
//        this.loginOfUser = loginOfUser;
//    }
//
//    public String getLoginOfUser() {
//        return loginOfUser;
//    }
//
//    public void setWeightOfAllProducts(double weightOfAllProducts) {
//        this.weightOfAllProducts += weightOfAllProducts;
//    }
//
//    public double getWeightOfAllProducts() {
//        return weightOfAllProducts;
//    }
//    public InTransport getProductInTransport(int numberOfProduct) {
//        return inTransportTab[numberOfProduct - 1];
//    }
//
//    public InTransport[] getInTransportTab() {
//        return inTransportTab;
//    }
}
