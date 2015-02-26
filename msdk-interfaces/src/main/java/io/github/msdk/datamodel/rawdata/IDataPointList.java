/* 
 * (C) Copyright 2015 by MSDK Development Team
 *
 * This software is dual-licensed under either
 *
 * (a) the terms of the GNU Lesser General Public License version 2.1
 * as published by the Free Software Foundation
 *
 * or (per the licensee's choosing)
 *
 * (b) the terms of the Eclipse Public License v1.0 as published by
 * the Eclipse Foundation.
 */

package io.github.msdk.datamodel.rawdata;

import java.util.List;

import javax.annotation.Nonnull;

/**
 * This interface provides a convenient data structure for storing large amount
 * of data points in mass spectra. Internally, it is implemented by two double[]
 * arrays, one for m/z values and one for intensity values. The arrays can be
 * resized dynamically, as in ArrayList. It provides all List methods, therefore
 * it supports iteration. Furthermore, it provides direct access to the
 * underlying buffer arrays, for more efficient data handling operations.
 * IDataPointList always keeps the data points sorted in the m/z order.
 * 
 * This data structure is not thread-safe.
 */
public interface IDataPointList extends List<IDataPoint> {

    /**
     * Returns the current m/z buffer array. The size of the array might be
     * larger than the actual size of this IDataPointList, therefore data
     * operations should always use the size returned by the size() method and
     * not the length of the returned array. The returned array reflects only
     * the current state of this list - if more data points are added, the
     * internal buffer might be replaced with a larger array.
     * 
     * @return Current m/z buffer
     */
    @Nonnull
    double[] getMzBuffer();

    /**
     * Returns the current intensity buffer array. The size of the array might
     * be larger than the actual size of this IDataPointList, therefore data
     * operations should always use the size returned by the size() method and
     * not the length of the returned array. The returned array reflects only
     * the current state of this list - if more data points are added, the
     * internal buffer might be replaced with a larger array.
     * 
     * @return Current intensity buffer
     */
    @Nonnull
    double[] getIntensityBuffer();

    /**
     * Sets the internal buffers to given arrays. The arrays will be referenced
     * directly without cloning. The m/z buffer contents must be sorted in
     * ascending order.
     * 
     * @param mzBuffer
     *            New m/z buffer
     * @param intensityBuffer
     *            New intensity buffer
     * @param size
     *            Number of data point items in the buffers. This might be
     *            smaller than the actual length of the buffer arrays.
     */
    void setBuffers(@Nonnull double[] mzBuffer,
	    @Nonnull double[] intensityBuffer, int size);
}