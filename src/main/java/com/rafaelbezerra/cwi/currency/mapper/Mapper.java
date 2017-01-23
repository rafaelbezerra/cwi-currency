package com.rafaelbezerra.cwi.currency.mapper;

import java.util.List;

/**
 * Mapper ganeric interface
 * 
 * @author rafaelbezerra
 *
 * @param <T>
 *            the object to be mapped to
 * @param <I>
 *            the object to be unmapped to
 */
public interface Mapper<T, I> {

	/**
	 * Maps an object type to other type
	 * 
	 * @param from
	 *            the object instance to be mapped
	 * @return the mapped object instance
	 */
	T map(I from);

	/**
	 * Maps an objects instancies list to other type
	 * 
	 * @param fromList
	 *            the object instancies list to be mapped
	 * @return the the mapped object list instancies
	 */
	List<T> mapList(List<I> fromList);

	/**
	 * Unmaps an object type to other type
	 * 
	 * @param from
	 *            the object instance to be unmapped
	 * @return the unmapped object instance
	 */
	I unmap(T from);

	/**
	 * Unmaps an objects instancies list to other type
	 * 
	 * @param fromList
	 *            the object instancies list to be unmapped
	 * @return the the unmapped object list instancies
	 */
	List<I> unmapList(List<T> fromList);

}