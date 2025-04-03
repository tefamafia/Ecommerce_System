
abstract class Product {
    protected String name;
    protected double price;
    protected int quantity;

    public Product (String name , double price , int quantity){
        this.name = name;
        this.price = price;
        this.quantity = quantity;

    }
      public boolean IsAvailable (int requestedQuantity)
      {
          return quantity>= requestedQuantity;
      }
      public void reduce (int quantity)
      {
          if(IsAvailable((quantity)))
          {

              this.quantity -= quantity;
          }
      }
      public String GetName()
      {
          return name;
      }
      public double GetPrice()
      {
          return price;
      }
      public int getQuantity()
      {
          return quantity;
      }
    public abstract boolean isShippable();
}
