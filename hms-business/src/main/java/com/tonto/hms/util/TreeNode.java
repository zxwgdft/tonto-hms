package com.tonto.hms.util;

import java.util.HashMap;
import java.util.Map;

public class TreeNode<T,K> {
	
	private K key;
	private TreeNode<T,K> parent;
	private T value;
	private Map<K,TreeNode<T,K>> children;
	
	public TreeNode(T value,K key)
	{
		this.key=key;
		this.value=value;
		children=new HashMap<K,TreeNode<T,K>>();
	}
	
	public void addChildNode(TreeNode<T,K> child)
	{
		if(child!=null)
		{
			K key=child.getKey();
			if(key!=null)	
				children.put(key, child);
		}
	}
	
	public void removeChildNode(K key)
	{
		if(key!=null)
			children.remove(key);
	}
	
	public K getKey() {
		return key;
	}
	public void setKey(K key) {
		this.key = key;
	}
	public TreeNode<T, K> getParent() {
		return parent;
	}
	public void setParent(TreeNode<T, K> parent) {
		this.parent = parent;
	}
	public Map<K, TreeNode<T, K>> getChildren() {
		return children;
	}
	public void setChildren(Map<K, TreeNode<T, K>> children) {
		this.children = children;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}
	
}
