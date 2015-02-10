package pset1;
public class D extends C {
	int g;
	public D(int f, int g) {
		super(f);
		this.g = g; }
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		D other = (D) obj;
		if (g != other.g)
			return false;
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + g;
		return result;
	}
}