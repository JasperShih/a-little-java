/*------------------------data type-----------------------------*/
/**FruitType*/
abstract class FruitType{}
class Peach extends FruitType{public boolean equals(Object obj) {return (obj instanceof Peach);}}
class Apple extends FruitType{public boolean equals(Object obj) {return (obj instanceof Apple);}}
class Pear extends FruitType{public boolean equals(Object obj) {return (obj instanceof Pear);}}
class Lemon extends FruitType{public boolean equals(Object obj) {return (obj instanceof Lemon);}}
class Fig extends FruitType{public boolean equals(Object obj) {return (obj instanceof Fig);}}

/**TreeType*/
abstract class TreeType{abstract Object accept(TreeVisitorI ask);}

class BaseTree extends TreeType{Object accept(TreeVisitorI ask) {return ask.forBaseTree();}}

class FlatExtendTree extends TreeType{
    FruitType car;
    TreeType cdr;
    FlatExtendTree(FruitType _car, TreeType _cdr) {car = _car;
        cdr = _cdr;}
    Object accept(TreeVisitorI ask) {return ask.forFlatExtendTree(car, cdr);}
}

class DeepExtendTree extends TreeType{
    TreeType car;
    TreeType cdr;
    DeepExtendTree(TreeType _car, TreeType _cdr) {car = _car;
        cdr = _cdr;}
    Object accept(TreeVisitorI ask) {return ask.forDeepExtendTree(car, cdr);}
}

/*--------------------------visitor-------------------------------*/
interface TreeVisitorI {
    Object forBaseTree();
    Object forFlatExtendTree(FruitType car, TreeType cdr);
    Object forDeepExtendTree(TreeType car, TreeType cdr);
}

class IsFlatV implements TreeVisitorI {
    public Object forBaseTree() {return new Boolean(true);}
    public Object forFlatExtendTree(FruitType car, TreeType cdr) {return cdr.accept(this);}
    public Object forDeepExtendTree(TreeType car, TreeType cdr) {return new Boolean(false);}
}

class IsDeepV implements TreeVisitorI {
    public Object forBaseTree() {return new Boolean(true);}
    public Object forFlatExtendTree(FruitType car, TreeType cdr) {return new Boolean(false);}
    public Object forDeepExtendTree(TreeType car, TreeType cdr) {return ((Boolean) (car.accept(this))) &&
                                                                     ((Boolean) (cdr.accept(this)));}
}

class OccursV implements TreeVisitorI{
    FruitType target;
    OccursV(FruitType _target) {target = _target;}
    public Object forBaseTree() {return new Integer(0);}
    public Object forFlatExtendTree(FruitType car, TreeType cdr) {if (target.equals(car))
                                                                  {return new Integer(1 + (Integer) (cdr.accept(this)));}
                                                               else {return cdr.accept(this);}}
    public Object forDeepExtendTree(TreeType car, TreeType cdr) {return new Integer(((Integer) (car.accept(this))) +
                                                                                  ((Integer) (cdr.accept(this))));}
}
