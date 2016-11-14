/*------------------------data type-----------------------------*/
/**FruitType*/
abstract class FruitType{}
class Peach extends FruitType{public boolean equals(Object obj) {return (obj instanceof Peach);}}
class Apple extends FruitType{public boolean equals(Object obj) {return (obj instanceof Apple);}}
class Pear extends FruitType{public boolean equals(Object obj) {return (obj instanceof Pear);}}
class Lemon extends FruitType{public boolean equals(Object obj) {return (obj instanceof Lemon);}}
class Fig extends FruitType{public boolean equals(Object obj) {return (obj instanceof Fig);}}

/**TreeType*/
abstract class TreeType{
  abstract boolean accept(bTreeVisitorI ask);
  abstract int accept(iTreeVisitorI ask);
  abstract TreeType accept(tTreeVisitorI ask);
}

class BaseTree extends TreeType{
  boolean accept(bTreeVisitorI ask) {return ask.forBaseTree();}
  int accept(iTreeVisitorI ask) {return ask.forBaseTree();}
  TreeType accept(tTreeVisitorI ask) {return ask.forBaseTree();}
}

class FlatExtendTree extends TreeType{
  FruitType car;
  TreeType cdr;
  FlatExtendTree(FruitType _car, TreeType _cdr) {car = _car;
                                                 cdr = _cdr;}
  boolean accept(bTreeVisitorI ask) {return ask.forFlatExtendTree(car, cdr);}
  int accept(iTreeVisitorI ask) {return ask.forFlatExtendTree(car, cdr);}
  TreeType accept(tTreeVisitorI ask) {return ask.forFlatExtendTree(car, cdr);}
}

class DeepExtendTree extends TreeType{
  TreeType car;
  TreeType cdr;
  DeepExtendTree(TreeType _car, TreeType _cdr) {car = _car;
                                                cdr = _cdr;}
  boolean accept(bTreeVisitorI ask) {return ask.forDeepExtendTree(car, cdr);}
  int accept(iTreeVisitorI ask) {return ask.forDeepExtendTree(car, cdr);}
  TreeType accept(tTreeVisitorI ask) {return ask.forDeepExtendTree(car, cdr);}
}

/*--------------------------visitor-------------------------------*/
/**bTreeVisitorI*/
interface bTreeVisitorI{
  boolean forBaseTree();
  boolean forFlatExtendTree(FruitType car, TreeType cdr);
  boolean forDeepExtendTree(TreeType car, TreeType cdr);
}

class bIsFlatV implements bTreeVisitorI{
  public boolean forBaseTree() {return true;}
  public boolean forFlatExtendTree(FruitType car, TreeType cdr) {return cdr.accept(this);}
  public boolean forDeepExtendTree(TreeType car, TreeType cdr) {return false;}
}

class bIsDeepV implements bTreeVisitorI{
  public boolean forBaseTree() {return true;}
  public boolean forFlatExtendTree(FruitType car, TreeType cdr) {return false;}
  public boolean forDeepExtendTree(TreeType car, TreeType cdr) {return (car.accept(this) &&
  																																		  cdr.accept(this));}
}

class bHasFruitV implements bTreeVisitorI{
  public boolean forBaseTree() {return false;}
  public boolean forFlatExtendTree(FruitType car, TreeType cdr) {return true;}
  public boolean forDeepExtendTree(TreeType car, TreeType cdr) {return car.accept(this) ||
                                                                       cdr.accept(this);}
}

/**iTreeVisitorI*/
interface iTreeVisitorI{
  int forBaseTree();
  int forFlatExtendTree(FruitType car, TreeType cdr);
  int forDeepExtendTree(TreeType car, TreeType cdr);
}

class iHeightV implements iTreeVisitorI{
  public int forBaseTree() {return 0;}
  public int forFlatExtendTree(FruitType car, TreeType cdr) {return 1 + cdr.accept(this);}
  public int forDeepExtendTree(TreeType car, TreeType cdr) {return 1 + Math.max(car.accept(this),
                                                                                cdr.accept(this));}
}

class iOccursV implements iTreeVisitorI{
  FruitType target;
  iOccursV(FruitType _target) {target = _target;}
  public int forBaseTree() {return 0;}
  public int forFlatExtendTree(FruitType car, TreeType cdr) {if (target.equals(car))
                                                                {return 1 + cdr.accept(this);}
                                                             else {return cdr.accept(this);}}
  public int forDeepExtendTree(TreeType car, TreeType cdr) {return car.accept(this) +
                                                                   cdr.accept(this);}
}

/**tTreeVisitorI*/
interface tTreeVisitorI{
  TreeType forBaseTree();
  TreeType forFlatExtendTree(FruitType car, TreeType cdr);
  TreeType forDeepExtendTree(TreeType car, TreeType cdr);
}

class tSubstV implements tTreeVisitorI{
  FruitType newOne;
  FruitType oldOne;
  tSubstV(FruitType _newOne, FruitType _oldOne) {newOne = _newOne;
                                                 oldOne = _oldOne;}
  public TreeType forBaseTree() {return new BaseTree();}
  public TreeType forFlatExtendTree(FruitType car, TreeType cdr) {FruitType returnFruit;
                                                                  if (oldOne.equals(car))
                                                                     {returnFruit = newOne;}
                                                                  else {returnFruit = car;}
                                                                  return new FlatExtendTree(returnFruit, cdr);}
  public TreeType forDeepExtendTree(TreeType car, TreeType cdr) {return new DeepExtendTree(car.accept(this),
                                                                                           cdr.accept(this));}
}

// new DeepExtendTree(
//   new DeepExtendTree(
//     new BaseTree(),
//     new DeepExtendTree(
//       new BaseTree(),
//       new BaseTree())),
//   new DeepExtendTree(
//     new BaseTree(),
//     new DeepExtendTree(
//       new BaseTree(),
//       new BaseTree()))).accept(new bIsDeepV());
// >
// true
