// method closerToO是template
// template method pattern instance就是abstract class裡的實作method?
// 而該實作method裡用到的abstract method就稱為hook?
abstract class PointD{
  int	x;
  int	y;
  abstract int distanceToO();
  public PointD(int _x, int _y) {x = _x;
                                 y = _y;}
  boolean closerToO(PointD p) {return distanceToO() <= p.distanceToO();}}

class CartesianPt extends PointD{
  public CartesianPt(int _x, int _y) {super(_x, _y);}
  int distanceToO() {return (int) Math.sqrt(x * x + y * y);}}

class ManhattanPt extends PointD{
  public ManhattanPt(int _x, int _y) {super(_x, _y);}
  int distanceToO() {return x + y;}}

abstract class ShishD {
  abstract boolean onlyOnions();
  abstract boolean isVegetarian();}

class Skewer extends ShishD{
  boolean onlyOnions() {return true;}
  boolean isVegetarian() {return true;}}

class Onion extends ShishD {
  ShishD	s;
  public Onion(ShishD _s) {s = _s;}
  boolean onlyOnions() {return s.onlyOnions();}
  boolean isVegetarian() {return s.isVegetarian();}}

class Lamb extends ShishD{
  ShishD	s;
  public Lamb(ShishD _s) {s = _s;}
  boolean onlyOnions() {return false;}
  boolean isVegetarian() {return false;}}

class Tomato extends ShishD{
  ShishD	s;
  public Tomato(ShishD _s) {s = _s;}
  boolean onlyOnions() {return false;}
  boolean isVegetarian() {return s.isVegetarian();}}

abstract class KebabD {
  abstract boolean isVeggie();
  abstract Object whatHolder();}

class Holder extends KebabD{
  Object	o;
  public Holder(Object _o) {o = _o;}
  boolean isVeggie() {return true;}
  Object whatHolder() {return o;}}

class Shallot extends KebabD {
  KebabD	k;
  public Shallot(KebabD _k) {k = _k;}
  boolean isVeggie() {return k.isVeggie();}
  Object whatHolder() {return k.whatHolder();}}

class Shrimp extends KebabD {
  KebabD	k;
  public Shrimp(KebabD _k) {k = _k;}
  boolean isVeggie() { return false;}
  Object whatHolder() {return k.whatHolder();}}

class Radish extends KebabD {
  KebabD	k;
  public Radish(KebabD _k) {k = _k;}
  boolean isVeggie() {return k.isVeggie();}
  Object whatHolder() {return k.whatHolder();}}

class Pepper extends KebabD {
  KebabD	k;
  public Pepper(KebabD _k) {k = _k;}
  boolean isVeggie() {return k.isVeggie();}
  Object whatHolder() {return k.whatHolder();}}

class Zucchini extends KebabD {
  KebabD	k;
  public Zucchini(KebabD _k) {k = _k;}
  boolean isVeggie() {return k.isVeggie();}
  Object whatHolder() {return k.whatHolder();}}

abstract class RodD {}
class Dagger extends RodD {}
class Sabre extends RodD {}
class Sword extends RodD {}

abstract class PlateD {}
class Gold extends PlateD {}
class Silver extends PlateD {}
class Brass extends PlateD {}
class Copper extends PlateD {}
class Wood extends PlateD {}

public class Chapter2 {
	public static void main(String[] args) {
		// System.out.println(new Onion(new Onion(new Skewer())).onlyOnions());
		System.out.println(new CartesianPt(5, 6).closerToO(new ManhattanPt(2, 4)));
	}
}
