/*---------------------------------------------------------data type-------------------------------------------------------------------*/
//ExpressionType
abstract class ExpressionType {abstract Object apply(ExpressionVisitorI function);}

class Constant extends ExpressionType {
    Object atom;
    Constant(Object _atom) {atom = _atom;}
    Object apply(ExpressionVisitorI function) {return function.forConstant(atom);}
}

class Plus extends ExpressionType {
    ExpressionType exp1;
    ExpressionType exp2;
    Plus(ExpressionType _exp1, ExpressionType _exp2) {exp1 = _exp1;
                                                      exp2 = _exp2;}
    Object apply(ExpressionVisitorI function) {return function.forPlus(exp1, exp2);}
}

class Diff extends ExpressionType {
    ExpressionType exp1;
    ExpressionType exp2;
    Diff(ExpressionType _exp1, ExpressionType _exp2) {exp1 = _exp1;
                                                      exp2 = _exp2;}
    Object apply(ExpressionVisitorI function) {return function.forDiff(exp1, exp2);}
}

class Product extends ExpressionType {
    ExpressionType exp1;
    ExpressionType exp2;
    Product(ExpressionType _exp1, ExpressionType _exp2) {exp1 = _exp1;
                                                         exp2 = _exp2;}
    Object apply(ExpressionVisitorI function) {return function.forProduct(exp1, exp2);}
}

//SetType
abstract class SetType{
    SetType add(Integer i) {if (isMember(i))
                               {return this;}
                            else {return new Add(i, this);}}
    abstract boolean isMember(Integer i);
    abstract SetType plus(SetType s);
    abstract SetType diff(SetType s);
    abstract SetType product(SetType s);
}

class Empty extends SetType{
    @Override boolean isMember(Integer i) {return false;}
    @Override SetType plus(SetType s) {return s;}
    @Override SetType diff(SetType s) {return new Empty();}
    @Override SetType product(SetType s) {return new Empty();}
}

class Add extends SetType{
    Integer i;
    SetType s;
    Add(Integer _i, SetType _s) {i = _i;
                                 s = _s;}
    @Override boolean isMember(Integer n) {if (i.equals(n))
                                              {return true;}
                                           else {return s.isMember(n);}}
    @Override SetType plus(SetType t) {return s.plus(t.add(i));}
    @Override SetType diff(SetType t) {if (t.isMember(i))
                                          {return s.diff(t);}
                                       else {return s.diff(t).add(i);}}
    @Override SetType product(SetType t) {if (t.isMember(i))
                                             {return s.product(t).add(i);}
                                          else {return s.product(t);}}
}

/*--------------------------------------------------------------------visitor---------------------------------------------------------------------*/
//interface: ExpressionVisitorI
interface ExpressionVisitorI{
    Object forConstant(Object atom);
    Object forPlus(ExpressionType exp1, ExpressionType exp2);
    Object forDiff(ExpressionType exp1, ExpressionType exp2);
    Object forProduct(ExpressionType exp1, ExpressionType exp2);
}

abstract class EvalType implements ExpressionVisitorI{
    @Override public Object forConstant(Object atom) {return atom;}
    @Override public Object forPlus(ExpressionType exp1, ExpressionType exp2) {return plus(exp1.apply(this),
                                                                                           exp2.apply(this));}
    @Override public Object forDiff(ExpressionType exp1, ExpressionType exp2) {return diff(exp1.apply(this),
                                                                                           exp2.apply(this));}
    @Override public Object forProduct(ExpressionType exp1, ExpressionType exp2) {return product(exp1.apply(this),
                                                                                                 exp2.apply(this));}
    abstract Object plus(Object exp1, Object exp2);
    abstract Object diff(Object exp1, Object exp2);
    abstract Object product(Object exp1, Object exp2);
}

class IntEvalV extends EvalType{
    @Override Object plus(Object exp1, Object exp2) {Integer intExp1 = ((Integer) exp1);
                                                     Integer intExp2 = ((Integer) exp2);
                                                     return new Integer(intExp1 + intExp2);}
    @Override Object diff(Object exp1, Object exp2) {Integer intExp1 = ((Integer) exp1);
                                                     Integer intExp2 = ((Integer) exp2);
                                                     return new Integer(intExp1 - intExp2);}
    @Override Object product(Object exp1, Object exp2) {Integer intExp1 = ((Integer) exp1);
                                                        Integer intExp2 = ((Integer) exp2);
                                                        return new Integer(intExp1 * intExp2);}

}

class SetEvalV extends EvalType{
    @Override Object plus(Object exp1, Object exp2) {SetType setExp1 = ((SetType) exp1);
                                                     SetType setExp2 = ((SetType) exp2);
                                                     return setExp1.plus(setExp2);}
    @Override Object diff(Object exp1, Object exp2) {SetType setExp1 = ((SetType) exp1);
                                                     SetType setExp2 = ((SetType) exp2);
                                                     return setExp1.diff(setExp2);}
    @Override Object product(Object exp1, Object exp2) {SetType setExp1 = ((SetType) exp1);
                                                        SetType setExp2 = ((SetType) exp2);
                                                        return setExp1.product(setExp2);}
}

//interface: PieVisitorI
/*
abstract class SubstType implements PieVisitorI{
    Object newStuff;
    Object oldStuff;
    SubstType(Object _newStuff, Object _oldStuff) {newStuff = _newStuff;
                                                   oldStuff = _oldStuff;}
    public PieType forBot() {return new Bot();}
    public abstract PieType forTop(Object t, PieType r);
}

class SubstV extends SubstType{
    SubstV(Object _newStuff, Object _oldStuff) {super(_newStuff, _oldStuff);}
    @Override public PieType forTop(Object t, PieType r) {return null;}
}

class LtdSubstV extends SubstType{
    int c;
    LtdSubstV(int _c, Object _newStuff, Object _oldStuff) {super(_newStuff, _oldStuff);
                                                           c = _c;}
    @Override public PieType forTop(Object t, PieType r) {return null;}
}
*/

/*--------------------------------------------------------------------------------------------------------------
class SubstV implements PieVisitorI{
    Object newStuff;
    Object oldStuff;
    SubstV(Object _newStuff, Object _oldStuff) {newStuff = _newStuff;
                                                oldStuff = _oldStuff;}
    public PieType forBot() {return new Bot();}
    public PieType forTop(Object t, PieType r) {if (oldStuff.equals(t))
                                                   {return new Top(newStuff, r.apply(this));}
                                                else {return new Top(t, r.apply(this));}}
}

class LtdSubstV extends SubstV{
    int c;
    LtdSubstV(int _c, Object _newStuff, Object _oldStuff) {super(_newStuff, _oldStuff);
                                                           c = _c;}
    @Override public PieType forTop(Object t, PieType r) {
        if (c == 0)
          {return new Top(t, r);}
          {if (oldStuff.equals(t))
              {return new Top(newStuff, r.apply(new LtdSubstV(c-1, newStuff, oldStuff)));}
           else {return new Top(t, r.apply(this));}}
}
*/
