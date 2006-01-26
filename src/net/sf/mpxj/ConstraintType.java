/*
 * file:       ConstraintType.java
 * author:     Jon Iles
 * copyright:  (c) Tapster Rock Limited 2002-2003
 * date:       01/02/2003
 */

/*
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation; either version 2.1 of the License, or (at your
 * option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation, Inc.,
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307, USA.
 */

package net.sf.mpxj;


/**
 * This class is used to represent a constraint type. It provides a mapping
 * between the textual description of a constraint type found in an MPX
 * file, and an enumerated representation that can be more easily manipulated
 * programatically.
 */
public final class ConstraintType
{
   /**
    * This constructor takes the numeric enumerated representation of a
    * constraint type and populates the class instance appropriately.
    * Note that unrecognised values are treated as "As Soon As Possible"
    * constraints.
    *
    * @param type int version of the constraint type
    */
   private ConstraintType (int type)
   {
      if (type < AS_SOON_AS_POSSIBLE_VALUE || type > FINISH_NO_LATER_THAN_VALUE)
      {
         m_type = AS_SOON_AS_POSSIBLE_VALUE;
      }
      else
      {
         m_type = type;
      }
   }

   /**
    * This method takes the integer enumeration of a constraint type
    * and returns an appropriate class instance. Note that unrecognised
    * values are treated as "As Soon As Possible" constraints.
    *
    * @param type integer constraint type enumeration
    * @return ConstraintType instance
    */
   public static ConstraintType getInstance (int type)
   {
      if (type < 0 || type >= TYPE_VALUES.length)
      {
         type = 0;
      }

      return (TYPE_VALUES[type]);
   }

   /**
    * This method takes the integer enumeration of a constraint type
    * and returns an appropriate class instance. Note that unrecognised
    * values are treated as "As Soon As Possible" constraints.
    *
    * @param type integer constraint type enumeration
    * @return ConstraintType instance
    */
   public static ConstraintType getInstance (Number type)
   {
      int index = 0;

      if (type != null)
      {
         index= type.intValue();
      }

      return (getInstance(index));
   }

   /**
    * Accessor method used to retrieve the numeric representation of the
    * constraint type.
    *
    * @return int representation of the constraint type
    */
   public int getType ()
   {
      return (m_type);
   }

   public static final int AS_SOON_AS_POSSIBLE_VALUE = 0;
   public static final int AS_LATE_AS_POSSIBLE_VALUE = 1;
   public static final int MUST_START_ON_VALUE = 2;
   public static final int MUST_FINISH_ON_VALUE = 3;
   public static final int START_NO_EARLIER_THAN_VALUE = 4;
   public static final int START_NO_LATER_THAN_VALUE = 5;
   public static final int FINISH_NO_EARLIER_THAN_VALUE = 6;
   public static final int FINISH_NO_LATER_THAN_VALUE = 7;

   public static final ConstraintType AS_SOON_AS_POSSIBLE = new ConstraintType(AS_SOON_AS_POSSIBLE_VALUE);
   public static final ConstraintType AS_LATE_AS_POSSIBLE = new ConstraintType(AS_LATE_AS_POSSIBLE_VALUE);
   public static final ConstraintType MUST_START_ON = new ConstraintType(MUST_START_ON_VALUE);
   public static final ConstraintType MUST_FINISH_ON = new ConstraintType(MUST_FINISH_ON_VALUE);
   public static final ConstraintType START_NO_EARLIER_THAN = new ConstraintType(START_NO_EARLIER_THAN_VALUE);
   public static final ConstraintType START_NO_LATER_THAN = new ConstraintType(START_NO_LATER_THAN_VALUE);
   public static final ConstraintType FINISH_NO_EARLIER_THAN = new ConstraintType(FINISH_NO_EARLIER_THAN_VALUE);
   public static final ConstraintType FINISH_NO_LATER_THAN = new ConstraintType(FINISH_NO_LATER_THAN_VALUE);

   private static final ConstraintType[] TYPE_VALUES =
   {
      AS_SOON_AS_POSSIBLE,
      AS_LATE_AS_POSSIBLE,
      MUST_START_ON,
      MUST_FINISH_ON,
      START_NO_EARLIER_THAN,
      START_NO_LATER_THAN,
      FINISH_NO_EARLIER_THAN,
      FINISH_NO_LATER_THAN
   };

   /**
    * Internal representation of the constraint type.
    */
   private int m_type;
}
