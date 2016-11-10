abstract class SeasoningD {}
class Salt extends SeasoningD {}
class Pepper extends SeasoningD {}

abstract class PointD {}

class CartesianPt extends PointD{
	int	x;
	int	y;
	public CartesianPt(int _x, int _y){
		x = _x;
		y = _y;}}

class ManhattanPt extends PointD{
	int	x;
	int	y;
	public ManhattanPt(int _x, int _y){
		x = _x;
		y = _y;}}

abstract class NumD {}
class Zero extends NumD {}
class OneMoreThan extends NumD{
	NumD	predecessor;
	public OneMoreThan(NumD _p){predecessor = _p;}}

abstract class LayerD {}
class Base extends LayerD {
	Object	o;
	public Base(Object _o) {o = _o;}}

class Slice extends LayerD {
	LayerD	l;
	public Slice(LayerD _l){l = _l;}}

public class Chapter1{
	public static void main(String[] args){
		LayerD aaa = new Base(6);
		if (aaa instanceof Object){System.out.println("yes");}
		else{System.out.println("no");}}}
