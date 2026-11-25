import java.util.ArrayList;

/**
 * A list of Strings that is always kept in ascending lexicographic order.
 * Binary search is implemented by hand (java.util.Collections/Arrays
 * binarySearch are NOT used).
 */
public class SortedList
{
    private final ArrayList<String> items = new ArrayList<>();

    /**
     * Binary search for the target.
     * @return the index of the target if present; otherwise
     *         -(insertionPoint) - 1, where insertionPoint is the index
     *         at which the target would be inserted.
     */
    private int binarySearch(String target)
    {
        int low = 0;
        int high = items.size() - 1;

        while (low <= high)
        {
            int mid = (low + high) >>> 1;
            int cmp = items.get(mid).compareTo(target);

            if (cmp < 0)
            {
                low = mid + 1;
            }
            else if (cmp > 0)
            {
                high = mid - 1;
            }
            else
            {
                return mid;
            }
        }
        return -(low + 1);
    }

    /** Inserts the string at the position that keeps the list sorted. Returns that index. */
    public int add(String s)
    {
        int pos = binarySearch(s);
        if (pos < 0)
        {
            pos = -(pos + 1);
        }
        items.add(pos, s);
        return pos;
    }

    /** @return index of the element if found, otherwise -(insertionPoint) - 1 */
    public int search(String s)
    {
        return binarySearch(s);
    }

    public boolean contains(String s)
    {
        return binarySearch(s) >= 0;
    }

    public String get(int index)
    {
        return items.get(index);
    }

    public int size()
    {
        return items.size();
    }

    public boolean isEmpty()
    {
        return items.isEmpty();
    }

    public void clear()
    {
        items.clear();
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < items.size(); i++)
        {
            sb.append(i).append(": ").append(items.get(i)).append("\n");
        }
        return sb.toString();
    }
}
