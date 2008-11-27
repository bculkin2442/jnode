/*
 * Copyright 2005-2006 Sun Microsystems, Inc.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Sun designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Sun in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Sun Microsystems, Inc., 4150 Network Circle, Santa Clara,
 * CA 95054 USA or visit www.sun.com if you need additional information or
 * have any questions.
 */
package com.sun.xml.internal.rngom.digested;

import com.sun.xml.internal.rngom.ast.builder.Annotations;
import com.sun.xml.internal.rngom.ast.builder.BuildException;
import com.sun.xml.internal.rngom.ast.builder.DataPatternBuilder;
import com.sun.xml.internal.rngom.ast.om.Location;
import com.sun.xml.internal.rngom.ast.om.ParsedElementAnnotation;
import com.sun.xml.internal.rngom.ast.om.ParsedPattern;
import com.sun.xml.internal.rngom.parse.Context;
import org.xml.sax.Locator;

/**
 * @author Kohsuke Kawaguchi (kk@kohsuke.org)
 */
final class DataPatternBuilderImpl implements DataPatternBuilder {

    private final DDataPattern p;

    public DataPatternBuilderImpl(String datatypeLibrary, String type, Location loc) {
        p = new DDataPattern();
        p.location = (Locator)loc;
        p.datatypeLibrary = datatypeLibrary;
        p.type = type;
    }

    public void addParam(String name, String value, Context context, String ns, Location loc, Annotations anno) throws BuildException {
        p.params.add(p.new Param(name,value,context.copy(),ns,loc,(Annotation)anno));
    }

    public void annotation(ParsedElementAnnotation ea) {
        // TODO
    }

    public ParsedPattern makePattern(Location loc, Annotations anno) throws BuildException {
        return makePattern(null,loc,anno);
    }

    public ParsedPattern makePattern(ParsedPattern except, Location loc, Annotations anno) throws BuildException {
        p.except = (DPattern)except;
        if(anno!=null)
            p.annotation = ((Annotation)anno).getResult();
        return p;
    }
}