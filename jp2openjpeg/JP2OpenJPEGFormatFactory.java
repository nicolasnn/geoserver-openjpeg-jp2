/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2007-2008, Open Source Geospatial Foundation (OSGeo)
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation;
 *    version 2.1 of the License.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 */
package org.geotools.coverageio.gdal.jp2openjpeg;

import it.geosolutions.imageio.plugins.jp2openjpeg.JP2GDALOpenJPEGImageReaderSpi;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.geotools.coverage.grid.io.GridFormatFactorySpi;
import org.geotools.coverageio.BaseGridFormatFactorySPI;
import org.opengis.coverage.grid.Format;

/**
 * Implementation of the {@link Format} service provider interface for JP2K files.
 *
 * @author Daniele Romagnoli, GeoSolutions
 * @author Simone Giannecchini (simboss), GeoSolutions
 * @since 2.5.x
 */
public final class JP2OpenJPEGFormatFactory extends BaseGridFormatFactorySPI
        implements GridFormatFactorySpi {
    /** Logger. */
    private static final Logger LOGGER =
            org.geotools.util.logging.Logging.getLogger(JP2OpenJPEGFormatFactory.class);

    /**
     * Tells me if the coverage plugin to access JP2K via OpenJPEG driver is available or not.
     *
     * @return {@code true} if the plugin is available, {@code false} otherwise.
     */
    public boolean isAvailable() {
        boolean available = true;

        // if these classes are here, then the runtime environment has
        // access to JAI and the JAI ImageI/O toolbox.
        try {
            Class.forName("it.geosolutions.imageio.plugins.jp2openjpeg.JP2GDALOpenJPEGImageReaderSpi");
            available = new JP2GDALOpenJPEGImageReaderSpi().isAvailable();

            if (LOGGER.isLoggable(Level.FINE)) {
                if (available) {
                    LOGGER.fine("JP2OpenJPEGFormatFactory is available.");
                } else {
                    LOGGER.fine("JP2OpenJPEGFormatFactory is not available.");
                }
            }
        } catch (ClassNotFoundException cnf) {
            if (LOGGER.isLoggable(Level.FINE)) {
                LOGGER.fine("JP2OpenJPEGFormatFactory is not available.");
            }

            available = false;
        }

        return available;
    }

    /**
     * Creating a {@link JP2OpenJPEGFormat}.
     *
     * @return A {@link JP2OpenJPEGFormat}.;
     */
    public JP2OpenJPEGFormat createFormat() {
        return new JP2OpenJPEGFormat();
    }
}