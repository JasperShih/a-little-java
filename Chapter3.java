abstract class PizzaD{
  abstract void show();
  abstract PizzaD removeAnchovy();
  abstract PizzaD addCheeseOnAnchovy();
  abstract PizzaD substituteAnchovyByCheese();
  //same as substituteAnchovyByCheese()
  abstract PizzaD mySub();}

class Crust extends PizzaD{
  PizzaD removeAnchovy() {return new Crust();}
  PizzaD addCheeseOnAnchovy() {return new Crust();}
  PizzaD substituteAnchovyByCheese() {return new Crust();}
  void show() {System.out.println(this.getClass().getSimpleName());}
  PizzaD mySub() {return addCheeseOnAnchovy().removeAnchovy();}}

class Cheese extends PizzaD{
  PizzaD p;
  public Cheese(PizzaD _p) {p = _p;}
  PizzaD removeAnchovy() {return new Cheese(p.removeAnchovy());}
  PizzaD addCheeseOnAnchovy() {return new Cheese(p.addCheeseOnAnchovy());}
  PizzaD substituteAnchovyByCheese() {return new Cheese(p.substituteAnchovyByCheese());}
  void show() {System.out.println(this.getClass().getSimpleName());
               p.show();}
  PizzaD mySub() {return addCheeseOnAnchovy().removeAnchovy();}}

class Olive extends PizzaD{
  PizzaD p;
  public Olive(PizzaD _p) {p = _p;}
  PizzaD removeAnchovy() {return new Olive(p.removeAnchovy());}
  PizzaD addCheeseOnAnchovy() {return new Olive(p.addCheeseOnAnchovy());}
  PizzaD substituteAnchovyByCheese() {return new Olive(p.substituteAnchovyByCheese());}
  void show() {System.out.println(this.getClass().getSimpleName());
               p.show();}
  PizzaD mySub() {return addCheeseOnAnchovy().removeAnchovy();}}

class Anchovy extends PizzaD{
  PizzaD p;
  public Anchovy(PizzaD _p) {p = _p;}
  PizzaD removeAnchovy() {return p.removeAnchovy();}
  PizzaD addCheeseOnAnchovy() {return new Cheese(
                                        new Anchovy(p.addCheeseOnAnchovy()));}
  PizzaD substituteAnchovyByCheese() {return new Cheese(p.substituteAnchovyByCheese());}
  void show() {System.out.println(this.getClass().getSimpleName());
               p.show();}
  PizzaD mySub() {return addCheeseOnAnchovy().removeAnchovy();}}

class Sausage extends PizzaD{
  PizzaD p;
  public Sausage(PizzaD _p) {p = _p;}
  PizzaD removeAnchovy() {return new Sausage(p.removeAnchovy());}
  PizzaD addCheeseOnAnchovy() {return new Sausage(p.addCheeseOnAnchovy());}
  PizzaD substituteAnchovyByCheese() {return new Sausage(p.substituteAnchovyByCheese());}
  void show() {System.out.println(this.getClass().getSimpleName());
               p.show();}
  PizzaD mySub() {return addCheeseOnAnchovy().removeAnchovy();}}

class Spinach extends PizzaD{
  PizzaD p;
  public Spinach(PizzaD _p) {p = _p;}
  void show() {System.out.println(this.getClass().getSimpleName());
               p.show();}
  PizzaD removeAnchovy() {return new Spinach(p.removeAnchovy());}
  PizzaD addCheeseOnAnchovy() {return new Spinach(p.addCheeseOnAnchovy());}
  PizzaD substituteAnchovyByCheese() {return new Spinach(p.substituteAnchovyByCheese());}
  PizzaD mySub() {return addCheeseOnAnchovy().removeAnchovy();}}


public class Chapter3 {
	public static void main(String[] args) {
    new Cheese(new Anchovy(new Sausage(new Crust()))).removeAnchovy().show();
//		>
//		Cheese
//		Sausage
//		Crust

		new Olive(new Anchovy(new Cheese(new Anchovy(new Crust())))).addCheeseOnAnchovy().show();
//		>
//		Olive
//		Cheese
//		Anchovy
//		Cheese
//		Cheese
//		Anchovy
//		Crust

		new Olive(new Anchovy(new Cheese(new Anchovy(new Crust())))).substituteAnchovyByCheese().show();
//		>
//		Olive
//		Cheese
//		Cheese
//		Cheese
//		Crust

		new Olive(new Anchovy(new Cheese(new Anchovy(new Crust())))).mySub().show();
//		Olive
//		Cheese
//		Cheese
//		Cheese
//		Crust
	}
}
