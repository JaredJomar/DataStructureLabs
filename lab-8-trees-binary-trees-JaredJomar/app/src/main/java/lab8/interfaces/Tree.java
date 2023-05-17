package lab8.interfaces;

public interface Tree<E> {
	public TreeNode<E> root();
	public TreeNode<E> left(TreeNode<E> p);
	public TreeNode<E> right(TreeNode<E> p);
	public TreeNode<E> sibling(TreeNode<E> p); 
	public boolean isEmpty();
	public int size();
	public int height(E e); /* TODO EXERCISE 1*/
	public boolean verifyCousins(E e1, E e2); /* TODO EXERCISE 2*/
	public int getLevelCount(int level); /* TODO EXERCISE 3 */
	public List<E> getLevelElements(int level); /* TODO EXERCISE 4 */
	boolean isComplete(); /* TODO EXERCISE 5 */

}
