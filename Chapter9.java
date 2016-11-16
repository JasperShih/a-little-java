/*-----------------------------------------------data type------------------------------------------------*/
//PointType
abstract class PointType{
    int x;
    int y;
    PointType(int _x, int _y) {x = _x;
                               y = _y;}
    boolean closerToO(PointType p) {return distanceToO() <= p.distanceToO();}
    PointType minus(PointType p) {return new CartesianPt(x-p.x, y-p.y);}
    abstract int distanceToO();
}

class CartesianPt extends PointType{
    CartesianPt(int _x, int _y) {super(_x, _y);}
    @Override int distanceToO() {return (int) Math.sqrt(x * x + y * y);}
}

class ShadowedCartesianPt extends CartesianPt{
    int x2;
    int y2;
    ShadowedCartesianPt(int _x, int _y, int _x2, int _y2) {super(_x, _y);
                                                           x2 = _x2;
                                                           y2 = _y2;}
    @Override int distanceToO() {return new CartesianPt(x+x2, y+y2).distanceToO();}
}

class ManhattanPt extends PointType{
    ManhattanPt(int _x, int _y) {super(_x, _y);}
    @Override int distanceToO() {return x + y;}
}

class  ShadowedManhattanPt extends ManhattanPt{
    int x2;
    int y2;
    ShadowedManhattanPt(int _x, int _y, int _x2, int _y2) {super(_x, _y);
                                                           x2 = _x2;
                                                           y2 = _y2;}
    @Override int distanceToO() {return super.distanceToO() + x2 + y2;}
}

//ShapType
abstract class ShapType{abstract boolean apply(ShapeVisitorI function);}

class Circle extends ShapType{
    int r;
    Circle(int _r) {r = _r;}
    @Override boolean apply(ShapeVisitorI function) {return function.forCircle(r);}
}

class Square extends ShapType{
    int s;
    Square(int _s) {s = _s;}
    @Override boolean apply(ShapeVisitorI function) {return function.forSquare(s);}
}

class Translate extends ShapType{
    PointType q;
    ShapType s;
    Translate(PointType _q, ShapType _s) {q = _q;
                                          s = _s;}
    @Override boolean apply(ShapeVisitorI function) {return function.forTrans(q, s);}
}

class Union extends ShapType{
    ShapType s;
    ShapType t;
    Union(ShapType _s, ShapType _t) {s = _s;
                                     t = _t;}
    @Override boolean apply(ShapeVisitorI function) {return ((UnionVisitorI) function).forUnion(s, t);}
}

/*-------------------------------------------------visitor-------------------------------------------------*/
//ShapeVisitorI
interface ShapeVisitorI{
    boolean forCircle(int r);
    boolean forSquare(int s);
    boolean forTrans(PointType q, ShapType s);
}

class HasPtV implements ShapeVisitorI{
    PointType p;
    HasPtV(PointType _p) {p = _p;}
    ShapeVisitorI newHasPt(PointType p) {return new HasPtV(p);}
    @Override public boolean forCircle(int r) {return p.distanceToO() <= r;}
    @Override public boolean forSquare(int s) {return  (p.x <= s) && (p.y <= s);}
    @Override public boolean forTrans(PointType q, ShapType s) {return s.apply(newHasPt(p.minus(q)));}
}

//UnionVisitorI
interface UnionVisitorI extends ShapeVisitorI{
    boolean forUnion(ShapType s, ShapType t);
}

class UnionHasPtV extends HasPtV implements UnionVisitorI{
    UnionHasPtV(PointType _p) {super(_p);}
    //利用了override, UnionHasPtV obj.newHasPt > UnionHasPtV obj.
    //而HasPtV obj.newHasPt > newHasPt obj.
    ShapeVisitorI newHasPt(PointType p) {return new UnionHasPtV(p);}
    public boolean forUnion(ShapType s, ShapType t) {return s.apply(this) || t.apply(this);}
}
