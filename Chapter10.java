/*--------------------------------------------------Data Type----------------------------------------------*/
abstract class PieD{ abstract Object accept(PieVisitorI ask);}
class Bot extends PieD{ Object accept(PieVisitorI ask) {return ask.forBot(this);}}
class Top extends PieD{
    Object t;
    PieD r;
    Top(Object _t, PieD _r) {t = _t;
                             r = _r;}
    Object accept(PieVisitorI ask) {return ask.forTop(this);}
}

class PiemanM implements PiemanI{
    PieD p = new Bot();
    public int addTop(Object t) {p = new Top(t, p);
                               　return occTop(t);}
    public int remTop(Object t) {p = (PieD) p.accept(new RemV(t));
                               　return occTop(t);}
    public int substTop(Object n, Object o) {p = (PieD) p.accept(new SubstV(n, o));
                                          　　return occTop(n);}
    public int occTop(Object o) {return ((Integer) p.accept(new OccursV (o))).intValue();}
}

abstract class PointD{
    int x;
    int y;
    PointD(int _x, int _y) {x = _x;
                          　y = _y;}

    boolean closerToO(PointD p) {return distanceToO() <= p.distanceToO();}
    PointD minus(PointD p) {return new CartesianPt(x-p.x, y-p.y);}
    int moveBy(int del_x, int del_y) {x = x + del_x;
                                      y = y + del_y;
                                      return distanceToO();}
    abstract int distanceToO();
}

class CartesianPt extends PointD{
    CartesianPt(int _x, int _y) {super(_x, _y);}
    @Override int distanceToO() {return (int) Math.sqrt(x * x + y * y);}
}

class ManhattanPt extends PointD{
    ManhattanPt(int _x, int _y) {super(_x, _y);}
    @Override int distanceToO() {return (x + y);}
}

class ShadowedManhattanPt extends ManhattanPt{
    int del_x;
    int del_y;
    public ShadowedManhattanPt(int _x, int _y, int _del_x, int _del_y) {super(_x, _y);
                                                                        del_x = _del_x;
                                                                        del_y = _del_y;}
    int distanceToO() {return (super.distanceToO() + del_x + del_y);}
}

/*--------------------------------------------------Interface----------------------------------------------------*/
interface PiemanI{
    int addTop(Object t);
    int remTop(Object t);
    int substTop(Object n, Object o);
    int occTop(Object o);
}

interface PieVisitorI{
    Object forBot(Bot that);
    Object forTop(Top that);
}

/*--------------------------------------------------Visitor----------------------------------------------------*/
//class OccursV implements PieVisitorI{
//    Object a;
//    OccursV(Object _a) {a = _a;}
//    @Override public Object forBot(Bot that) {return (new Integer(0));}
//    @Override public Object forTop(Top that) {
//        if (that.t.equals(a))
//          {return new Integer(((Integer) (that.r.accept(this))).intValue() + 1);}
//        else {return that.r.accept(this);}
//    }
//}

//---Real Visitor Pattern
class SubstV implements PieVisitorI{
    Object n;
    Object o;
    SubstV(Object _n, Object _o) {n = _n;
                                 　o = _o;}
    @Override public Object forBot(Bot that) {return that;}
    @Override public Object forTop(Top that) {
        if (o.equals(that.t))
          {that.t = n;
           that.r.accept(this);
           return that;}
        else {that.r.accept(this);
             return that;}
    }
}

//class RemV implements PieVisitorI{
//    Object o;
//    RemV(Object _o) {o = _o;}
//    @Override public Object forBot(Bot that) {return (new Bot());}
//    @Override public Object forTop(Top that) {
//        if (o.equals(that.t))
//          {return that.r.accept(this);}
//        else {return (new Top(that.t, (PieD) that.r.accept(this)));}
//    }
//}
