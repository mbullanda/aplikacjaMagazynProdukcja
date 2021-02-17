package pl.michal.production;

public class Production {
    private int intermediateOneQuantity;
    private int intermediateTwoQuantity;
    private int intermediateThreeQuantity;
    private int pigmentQuantity;
    private double weightOfAllProducts;
    private double weightOfAllIntermediates;
    private double productionCapacity;
    private ProductionHistory productionHistory;

    public Production(){

    }
    public Production(ProductionHistory productionHistory){
        this.productionHistory = productionHistory;
    }
    public Production(int intermediateOneQuantity, int intermediateTwoQuantity, int intermediateThreeQuantity, int pigmentQuantity) {
        this.intermediateOneQuantity = intermediateOneQuantity;
        this.intermediateTwoQuantity = intermediateTwoQuantity;
        this.intermediateThreeQuantity = intermediateThreeQuantity;
        this.pigmentQuantity = pigmentQuantity;
    }

    public void setIntermediateOneQuantity(int intermediateOneQuantity) {
        this.intermediateOneQuantity += intermediateOneQuantity;
    }

    public void setIntermediateThreeQuantity(int intermediateThreeQuantity) {
        this.intermediateThreeQuantity += intermediateThreeQuantity;
    }

    public void setIntermediateTwoQuantity(int intermediateTwoQuantity) {
        this.intermediateTwoQuantity += intermediateTwoQuantity;
    }

    public void setPigmentQuantity(int pigmentQuantity) {
        this.pigmentQuantity += pigmentQuantity;
    }

    public int getIntermediateOneQuantity() {
        return intermediateOneQuantity;
    }

    public int getIntermediateThreeQuantity() {
        return intermediateThreeQuantity;
    }

    public int getIntermediateTwoQuantity() {
        return intermediateTwoQuantity;
    }

    public int getPigmentQuantity() {
        return pigmentQuantity;
    }

    public void setWeightOfAllProducts(double weightOfAllProducts) {
        this.weightOfAllProducts += weightOfAllProducts;
    }

    public double getWeightOfAllProducts() {
        return weightOfAllProducts;
    }

    public void setWeightOfAllIntermediates(double weightOfAllIntermediates) {
        this.weightOfAllIntermediates += weightOfAllIntermediates;
    }

    public double getWeightOfAllIntermediates() {
        return weightOfAllIntermediates;
    }

    public void setProductionCapacity(double productionCapacity) {
        this.productionCapacity += productionCapacity;
    }

    public double getProductionCapacity() {
        return productionCapacity;
    }
}
