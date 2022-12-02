package lesson5.labs.prob3;

class Item implements IItem{
	String name;
	private Item(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	@Override
	public String toString() {
		return name;
	}
	static IItem createItem(String name) {
		return new Item(name);
	}
}
