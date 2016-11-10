class RmAnchovyV{
  PieD forBot() {return new Bot();}
  PieD forTop(Object t, PieD r) {if (t instanceof Anchovy)
                                    {return r.rmAnchovy();}
                                 else {return new Top(t, r.rmAnchovy());}}
}

class RmFishV{
  PieD forBot(FishD f) {return new Bot();}
  //if判斷句中的t若在前面, 如果該物件改equals變成always true就有bug了.
  //we have to overwrite "equals" function before we use it.
  PieD forTop(Object t, PieD r, FishD f) {if (f.equals(t))
                                             {return r.rmFish(f);}
                                          else {return new Top(t, r.rmFish(f));}}
}

class RmIntV{
  PieD forBot(Integer i) {return new Bot();}
  PieD forTop(Object t, PieD r, Integer i) {if (i.equals(t))
                                               {return r.rmInt(i);}
                                            else {return new Top(t, r.rmInt(i));}}
}

class RemoveV{
  PieD forBot(Object o) {return new Bot();}
  PieD forTop(Object t, PieD r, Object o) {if (o.equals(t))
                                              {return r.remove(o);}
                                           else {return new Top(t, r.remove(o));}}
}

class SubstV{
  PieD forBot(Object oldO, Object newO) {return new Bot();}
  PieD forTop(Object t, PieD r, Object oldO, Object newO) {if (oldO.equals(t))
                                                              //感覺若subst多個物件, 都會是同一個newO, 會ref到同樣物件?
                                                              {return new Top(newO, r.subst(oldO, newO));}
                                                           else {return new Top(t, r.subst(oldO, newO));}}
}

class ShowV{
  void forBot() {System.out.println("Bot");}
  void forTop(Object t, PieD r) {System.out.println(t.getClass().getSimpleName());
                                 r.show();}
}

abstract class PieD{
  RmAnchovyV rmAnchovyV = new RmAnchovyV();
  RmFishV rmFishV = new RmFishV();
  RmIntV rmIntV = new RmIntV();
  RemoveV removeV = new RemoveV();
  SubstV substV = new SubstV();
  ShowV showV = new ShowV();
  abstract PieD rmAnchovy();
  abstract PieD rmFish(FishD f);
  abstract PieD rmInt(Integer i);
  abstract PieD remove(Object o);
  abstract PieD subst(Object oldO, Object newO);
  abstract void show();
}

class Bot extends PieD{
  PieD rmAnchovy() {return rmAnchovyV.forBot();}
  PieD rmFish(FishD f) {return rmFishV.forBot(f);}
  PieD rmInt(Integer i) {return rmIntV.forBot(i);}
  PieD remove(Object o) {return removeV.forBot(o);}
  PieD subst(Object oldO, Object newO) {return substV.forBot(oldO, newO);}
  void show() {showV.forBot();}
}

class Top extends PieD{
  Object t;
  PieD r;
  Top(Object _t, PieD _r){t = _t;
                          r = _r;}
  PieD rmAnchovy() {return rmAnchovyV.forTop(t, r);}
  PieD rmFish(FishD f) {return rmFishV.forTop(t, r, f);}
  PieD rmInt(Integer i) {return rmIntV.forTop(t, r, i);}
  PieD remove(Object o) {return removeV.forTop(t, r, o);}
  PieD subst(Object oldO, Object newO) {return substV.forTop(t, r, oldO, newO);}
  void show() {showV.forTop(t, r);}
}

abstract class FishD{}
class Anchovy extends FishD{
  /*because the function, we want to overwrite, is in other package,
    so we have to use public*/
  public boolean equals(Object o) {return (o instanceof Anchovy);}
}
class Salmon extends FishD{
  public boolean equals(Object o) {return (o instanceof Salmon);}
}
class Tuna extends FishD{
  public boolean equals(Object o) {return (o instanceof Tuna);}
}

abstract class NumD{}
class Zero extends NumD{
  public boolean equals(Object o) {return (o instanceof Zero);}
}
class OneMoreThan extends NumD{
  NumD predecessor;
  OneMoreThan(NumD _p) {predecessor = _p;}
  public boolean equals(Object o) {if (o instanceof OneMoreThan)
                                      {return predecessor.equals(((OneMoreThan) o).predecessor);}
                                   else {return false;}}
}

/*---------------------------------main--------------------------------------*/
public class Chapter4{
  public static void main(String[] args){
    new Top(new Anchovy(),
      new Top(new Tuna(),
        new Top(new Anchovy(),
          new Bot()))).show();
    // Anchovy
    // Tuna
    // Anchovy
    // Bot
    new Top(new Anchovy(),
      new Top(new Tuna(),
        new Top(new Anchovy(),
          new Bot()))).rmAnchovy().show();
    // Tuna
    // Bot
    new Top(new Anchovy(),
      new Top(new Tuna(),
        new Top(new Anchovy(),
          new Bot()))).rmFish(new Anchovy()).show();
    // Tuna
    // Bot
  }
}
