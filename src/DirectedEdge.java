public class DirectedEdge
{
    public final int v; // edge source
    public final int w; // edge target
    public final double weight; // edge weight
    public DirectedEdge(int v, int w, double weight)
    {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }
    public double weight()
    { return weight; }
    public int from()
    { return v; }
    public int to()
    { return w; }
    public String toString()
    { return String.format("%d->%d %.2f", v, w, weight); }
}

