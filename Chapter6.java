/*--------------------------data type---------------------------*/
abstract class PieD{
  abstract PieD accept(PieVisitorI ask);
}

class Top extends PieD{
  Object t;
  PieD r;
  Top(Object _t, PieD _r) {t = _t;
                           r = _r;}
  PieD accept(PieVisitorI ask) {return ask.forTop(t, r);}
}

class Bot extends PieD{
  PieD accept(PieVisitorI ask) {return ask.forBot();}
}

/*--------------------------visitor---------------------------*/
interface PieVisitorI{
  PieD forBot();
  PieD forTop(Object t, PieD r);
}

class RemV implements PieVisitorI{
  Object removeTarget;
  RemV(Object _removeTarget) {removeTarget = _removeTarget;}
  public PieD forBot() {return new Bot();}
  public PieD forTop(Object t, PieD r) {if (removeTarget.equals(t))
                                           {return r.accept(this);}
                                        else {return new Top(t, r.accept(this));}}
}

class SubstV implements PieVisitorI{
  Object newStuff;
  Object oldStuff;
  SubstV(Object _newStuff, Object _oldStuff) {newStuff = _newStuff;
                                              oldStuff = _oldStuff;}
  public PieD forBot() {return new Bot();}
  public PieD forTop(Object t, PieD r) {if (oldStuff.equals(t))
                                           {return new Top(newStuff, r.accept(this));}
                                        else {return new Top(t, r.accept(this));}}
}

class AtMostSubstV implements PieVisitorI{
  int times;
  Object newStuff;
  Object oldStuff;
  AtMostSubstV(int _times,
               Object _newStuff,
               Object _oldStuff) {times = _times;
                                  newStuff = _newStuff;
                                  oldStuff = _oldStuff;}
  public PieD forBot() {return new Bot();}
  public PieD forTop(Object t, PieD r) {
    if (times == 0)
       {return new Top(t, r);}
    else {if (oldStuff.equals(t))
             {return new Top(newStuff, r.accept(new AtMostSubstV(times-1,
                                                                 newStuff,
                                                                 oldStuff)));}
          else {return new Top(t, r.accept(this));}}}
}


// new Top(new Anchovy(),
//   new Top(new Tuna(),
//     new Top(new Anchovy(),
//       new Top(new Tuna(),
//         new Top(new Anchovy(),
//           new Bot()))))).accept(new AtMostSubstV(2,
//                                                  new Salmon(),
//                                                  new Anchovy))
// >
// new Top(new Salmon(),
//   new Top(new Tuna(),
//     new Top(new Salmon(),
//       new Top(new Tuna(),
//         new Top(new Anchovy(),
//           new Bot())))))
  
