/*------------------------------ShishD----------------------------------------*/
class OnlyOnionsV{
  boolean forSkewer(){return true;}
  boolean forOnion(ShishD s){return s.onlyOnions();}
  boolean forLamb(ShishD s){return false;}
  boolean forTomato(ShishD s){return false;}
 }

class IsVegetarianV{
  boolean forSkewer() {return true;}
  boolean forOnion(ShishD s) {return s.isVegetarian();}
  boolean forLamb(ShishD s) {return false;}
  boolean forTomato(ShishD s) {return s.isVegetarian();}
}

abstract class ShishD{
  OnlyOnionsV ooFn = new OnlyOnionsV();
  IsVegetarianV ivFn = new IsVegetarianV();
	abstract boolean onlyOnions();
  abstract boolean isVegetarian();
}

class Skewer extends ShishD{
  boolean onlyOnions() {return ooFn.forSkewer();}
  boolean isVegetarian() {return ivFn.forSkewer();}
}

class Onion extends ShishD{
  ShishD s;
  public Onion(ShishD _s) {s = _s;}
	boolean onlyOnions() {return ooFn.forOnion(s);}
  boolean isVegetarian() {return ivFn.forOnion(s);}
}

class Lamb extends ShishD{
  ShishD s;
  public Lamb(ShishD _s) {s = _s;}
	boolean onlyOnions() {return ooFn.forLamb(s);}
  boolean isVegetarian() {return ivFn.forOnion(s);}
}

class Tomato extends ShishD{
  ShishD s;
	public Tomato(ShishD _s) {s = _s;}
	boolean onlyOnions() {return ooFn.forTomato(s);}
  boolean isVegetarian() {return ivFn.forTomato(s);}
}

/*--------------------------------PizzaD--------------------------------------*/
class RmAnchovyV{
  PizzaD forCrust(){return new Crust();}
  PizzaD forCheese(PizzaD p){return new Cheese(p.rmAnchovy());}
  PizzaD forOlive(PizzaD p){return new Olive(p.rmAnchovy());}
  PizzaD forAnchovy(PizzaD p){return p.rmAnchovy();}
  PizzaD forSausage(PizzaD p){return new Sausage(p.rmAnchovy());}
}

class AddCheeseOnAnchovyV{
  PizzaD forCrust(){return new Crust();}
  PizzaD forCheese(PizzaD p){return new Cheese(p.addCheeseOnAnchovy());}
  PizzaD forOlive(PizzaD p){return new Olive(p.addCheeseOnAnchovy());}
  PizzaD forAnchovy(PizzaD p){return new Cheese(new Anchovy(p.addCheeseOnAnchovy()));}
  PizzaD forSausage(PizzaD p){return new Sausage(p.addCheeseOnAnchovy());}
}

class SubAnchovyByCheeseV{
  PizzaD forCrust(){return new Crust();}
  PizzaD forCheese(PizzaD p){return new Cheese(p.subAnchovyByCheese());}
  PizzaD forOlive(PizzaD p){return new Olive(p.subAnchovyByCheese());}
  PizzaD forAnchovy(PizzaD p){return new Cheese(p.subAnchovyByCheese());}
  PizzaD forSausage(PizzaD p){return new Sausage(p.subAnchovyByCheese());}
}

abstract class PizzaD{
  RmAnchovyV rmAnchovyV = new RmAnchovyV();
  AddCheeseOnAnchovyV addCheeseOnAnchovyV = new AddCheeseOnAnchovyV();
  SubAnchovyByCheeseV subAnchovyByCheeseV = new SubAnchovyByCheeseV();
  abstract PizzaD rmAnchovy();
  abstract PizzaD addCheeseOnAnchovy();
  abstract PizzaD subAnchovyByCheese();
}

class Crust extends PizzaD{
  PizzaD rmAnchovy() {return rmAnchovyV.forCrust();}
  PizzaD addCheeseOnAnchovy() {return addCheeseOnAnchovyV.forCrust();}
  PizzaD subAnchovyByCheese() {return subAnchovyByCheeseV.forCrust();}
}

class Cheese extends PizzaD{
  PizzaD p;
  Cheese(PizzaD _p){p = _p;}
  PizzaD rmAnchovy() {return rmAnchovyV.forCheese(p);}
  PizzaD addCheeseOnAnchovy() {return addCheeseOnAnchovyV.forCheese(p);}
  PizzaD subAnchovyByCheese() {return subAnchovyByCheeseV.forCheese(p);}
}

class Olive extends PizzaD{
  PizzaD p;
  Olive(PizzaD _p){p = _p;}
  PizzaD rmAnchovy() {return rmAnchovyV.forOlive(p);}
  PizzaD addCheeseOnAnchovy() {return addCheeseOnAnchovyV.forOlive(p);}
  PizzaD subAnchovyByCheese() {return subAnchovyByCheeseV.forOlive(p);}
}

class Anchovy extends PizzaD{
  PizzaD p;
  Anchovy(PizzaD _p){p = _p;}
  PizzaD rmAnchovy() {return rmAnchovyV.forAnchovy(p);}
  PizzaD addCheeseOnAnchovy() {return addCheeseOnAnchovyV.forAnchovy(p);}
  PizzaD subAnchovyByCheese() {return subAnchovyByCheeseV.forAnchovy(p);}
}

class Sausage extends PizzaD{
  PizzaD p;
  Sausage(PizzaD _p){p = _p;}
  PizzaD rmAnchovy() {return rmAnchovyV.forSausage(p);}
  PizzaD addCheeseOnAnchovy() {return addCheeseOnAnchovyV.forSausage(p);}
  PizzaD subAnchovyByCheese() {return subAnchovyByCheeseV.forSausage(p);}
}
/*--------------------------------main----------------------------------------*/
public class Chapter4{
  public static void main(String[] args){
  System.out.println();
  }
}
