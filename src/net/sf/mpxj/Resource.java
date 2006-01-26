/*
 * file:       Resource.java
 * author:     Scott Melville
 *             Jon Iles
 * copyright:  (c) Tapster Rock Limited 2002-2003
 * date:       15/08/2002
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

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import net.sf.mpxj.utility.BooleanUtility;
import net.sf.mpxj.utility.NumberUtility;


/**
 * This class represents the Resource record as found in an MPX file.
 */
public final class Resource extends ProjectEntity implements Comparable, ExtendedAttributeContainer
{
   /**
    * Default constructor.
    *
    * @param file the parent file to which this record belongs.
    */
   Resource (ProjectFile file)
   {
      super (file);

      if (file.getAutoResourceUniqueID() == true)
      {
         setUniqueID (new Integer(file.getResourceUniqueID ()));
      }

      if (file.getAutoResourceID() == true)
      {
         setID (new Integer(file.getResourceID ()));
      }
   }


   /**
    * Sets Name field value.
    *
    * @param val value
    */
   public void setName (String val)
   {
      set (NAME, val);
   }

   /**
    * Gets Resource Name field value.
    *
    * @return value
    */
   public String getName ()
   {
      return ((String)get(NAME));
   }

   /**
    * Set the resource type. Can be TYPE_MATERIAL, or TYPE_WORK.
    *
    * @param type resource type
    */
   public void setType (ResourceType type)
   {
      m_type = type;
   }

   /**
    * Retrieves the resource type. Can return TYPE_MATERIAL, or TYPE_WORK.
    *
    * @return resource type
    */
   public ResourceType getType ()
   {
      return (m_type);
   }

   /**
    * Set the flag indicating that this is a null resource.
    *
    * @param isNull null resource flag
    */
   public void setIsNull (boolean isNull)
   {
      m_null = isNull;
   }

   /**
    * Retrieve a flag indicating if this is a null resource.
    *
    * @return boolean flag
    */
   public boolean getNull ()
   {
      return (m_null);
   }

   /**
    * Sets Initials field value.
    *
    * @param val value
    */
   public void setInitials (String val)
   {
      set (INITIALS, val);
   }

   /**
    * Gets Initials of name field value.
    *
    * @return value
    */
   public String getInitials ()
   {
      return ((String)get(INITIALS));
   }

   /**
    * Sets phonetic information for the Japanese version of MS Project.
    *
    * @param phonetics Japanese phonetic information
    */
   public void setPhonetics (String phonetics)
   {
      m_phonetics = phonetics;
   }

   /**
    * Retrieves phonetic information for the Japanese version of MS Project.
    *
    * @return Japanese phonetic information
    */
   public String getPhonetics ()
   {
      return (m_phonetics);
   }

   /**
    * Sets the Windows account name for a resource.
    *
    * @param ntAccount windows account name
    */
   public void setNtAccount (String ntAccount)
   {
      m_ntAccount = ntAccount;
   }

   /**
    * Retrieves the Windows account name for a resource.
    *
    * @return windows account name
    */
   public String getNtAccount ()
   {
      return (m_ntAccount);
   }

   /**
    * Set the units label for a metarial resource.
    *
    * @param materialLabel material resource units label
    */
   public void setMaterialLabel (String materialLabel)
   {
      m_materialLabel = materialLabel;
   }

   /**
    * Retrieves the units label for a metarial resource.
    *
    * @return material resource units label
    */
   public String getMaterialLabel ()
   {
      return (m_materialLabel);
   }

   /**
    * Sets code field value.
    *
    * @param val value
    */
   public void setCode (String val)
   {
      set (CODE, val);
   }

   /**
    * Gets code field value.
    *
    * @return value
    */
   public String getCode ()
   {
      return ((String)get(CODE));
   }

   /**
    * Sets Group field value.
    *
    * @param val value
    */
   public void setGroup (String val)
   {
      set (GROUP, val);
   }

   /**
    * Gets Group field value.
    *
    * @return value
    */
   public String getGroup ()
   {
      return ((String)get(GROUP));
   }

   /**
    * Set the messaging method used to communicate with a project team.
    *
    * @param workGroup messaging method
    */
   public void setWorkGroup (WorkGroup workGroup)
   {
      m_workGroup = workGroup;
   }

   /**
    * Retrieve the messaging method used to communicate with a project team.
    *
    * @return messaging method
    */
   public WorkGroup getWorkGroup ()
   {
      return (m_workGroup);
   }

   /**
    * Set the resource's email address.
    *
    * @param emailAddress email address
    */
   public void setEmailAddress (String emailAddress)
   {
      set (EMAIL_ADDRESS, emailAddress);
   }

   /**
    * Retrieves the resource's email address.
    *
    * @return email address
    */
   public String getEmailAddress ()
   {
      return ((String)get(EMAIL_ADDRESS));
   }

   /**
    * Sets the hyperlink text.
    *
    * @param hyperlink hyperlink text
    */
   public void setHyperlink (String hyperlink)
   {
      m_hyperlink = hyperlink;
   }

   /**
    * Retrieves the hyperlink text.
    *
    * @return hyperlink text
    */
   public String getHyperlink ()
   {
      return (m_hyperlink);
   }

   /**
    * Sets the hyperlink address.
    *
    * @param hyperlinkAddress hyperlink address
    */
   public void setHyperlinkAddress (String hyperlinkAddress)
   {
      m_hyperlinkAddress = hyperlinkAddress;
   }

   /**
    * Retrieves the hyperlink address.
    *
    * @return hyperlink address
    */
   public String getHyperlinkAddress ()
   {
      return (m_hyperlinkAddress);
   }

   /**
    * Sets the hyperlink sub-address.
    *
    * @param hyperlinkSubAddress hyperlink sub-address
    */
   public void setHyperlinkSubAddress (String hyperlinkSubAddress)
   {
      m_hyperlinkSubAddress = hyperlinkSubAddress;
   }

   /**
    * Retrieves the hyperlink sub-address.
    *
    * @return hyperlink sub-address
    */
   public String getHyperlinkSubAddress ()
   {
      return (m_hyperlinkSubAddress);
   }

   /**
    * Sets the maximum availability of a resource.
    *
    * @param maxUnits maximum availability
    */
   public void setMaxUnits (Number maxUnits)
   {
      set (MAX_UNITS, maxUnits);
   }

   /**
    * Retrieves the maximum availability of a resource.
    *
    * @return maximum availability
    */
   public Number getMaxUnits ()
   {
      return ((Number)get(MAX_UNITS));
   }

   /**
    * Sets peak resource utilisation.
    *
    * @param peakUnits peak resource utilisation
    */
   public void setPeakUnits (Number peakUnits)
   {
      set (PEAK_UNITS, peakUnits);
   }

   /**
    * Retrieves the peak resource utilisation.
    *
    * @return peak resource utilisation
    */
   public Number getPeakUnits ()
   {
      return ((Number)get(PEAK_UNITS));
   }

   /**
    * Set the overallocated flag.
    *
    * @param overallocated overallocated flag
    */
   public void setOverAllocated (boolean overallocated)
   {
      set (OVERALLOCATED, overallocated);
   }

   /**
    * Retrieves the overallocated flag.
    *
    * @return overallocated flag
    */
   public boolean getOverAllocated ()
   {
      return (BooleanUtility.getBoolean((Boolean)get(OVERALLOCATED)));
   }

   /**
    * Retrieves the "available from" date.
    *
    * @return available from date
    */
   public Date getAvailableFrom ()
   {
      return (m_availableFrom);
   }

   /**
    * Set the "available from" date.
    *
    * @param date available from date
    */
   public void setAvailableFrom (Date date)
   {
      m_availableFrom = date;
   }

   /**
    * Retrieves the "available to" date.
    *
    * @return available from date
    */
   public Date getAvailableTo ()
   {
      return (m_availableTo);
   }

   /**
    * Set the "available to" date.
    *
    * @param date available to date
    */
   public void setAvailableTo (Date date)
   {
      m_availableTo = date;
   }

   /**
    * Sets the earliest start date for all assigned tasks.
    *
    * @param start start date
    */
   public void setStart (Date start)
   {
      m_start = start;
   }

   /**
    * Retrieves the earliest start date for all assigned tasks.
    *
    * @return start date
    */
   public Date getStart ()
   {
      return (m_start);
   }

   /**
    * Sets the latest finish date for all assigned tasks.
    *
    * @param finish finish date
    */
   public void setFinish (Date finish)
   {
      m_finish = finish;
   }

   /**
    * Retrieves the latest finish date for all assigned tasks.
    *
    * @return finish date
    */
   public Date getFinish ()
   {
      return (m_finish);
   }

   /**
    * Sets the flag indicating if the resource levelling can be applied
    * to this resource.
    *
    * @param canLevel boolean flag
    */
   public void setCanLevel (boolean canLevel)
   {
      m_canLevel = canLevel;
   }

   /**
    * Retrieves the flag indicating if the resource levelling can be applied
    * to this resource.
    *
    * @return boolean flag
    */
   public boolean getCanLevel ()
   {
      return (m_canLevel);
   }

   /**
    * Sets the Accrue at type.The Accrue At field provides choices for how
    * and when resource standard
    * and overtime costs are to be charged, or accrued, to the cost of a task.
    * The options are: Start, End and Prorated (Default)
    *
    * @param type accrue type
    */
   public void setAccrueAt (AccrueType type)
   {
      set (ACCRUE_AT, type);
   }

   /**
    * Gets the Accrue at type.The Accrue At field provides choices for how
    * and when resource standard
    * and overtime costs are to be charged, or accrued, to the cost of a task.
    * The options are: Start, End and Proraetd (Default)
    *
    * @return accrue type
    */
   public AccrueType getAccrueAt ()
   {
      return ((AccrueType)get(ACCRUE_AT));
   }

   /**
    * This field is ignored on import into MS Project.
    *
    * @param val - value to be set
    */
   public void setWork (Duration val)
   {
      set (WORK, val);
   }

   /**
    * Gets Work field value.
    *
    * @return value
    */
   public Duration getWork ()
   {
      return ((Duration)get(WORK));
   }

   /**
    * Retrieve the value of the regular work field.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Regular work value
    */
   public Duration getRegularWork ()
   {
      return (m_regularWork);
   }

   /**
    * Set the value of the regular work field.
    * Note that this value is an extension to the MPX specification.

    * @param duration Regular work value
    */
   public void setRegularWork (Duration duration)
   {
      m_regularWork = duration;
   }

   /**
    * Sets the Actual Work field contains the amount of work that has already
    * been done for all assignments assigned to a resource.
    *
    * @param val duration value
    */
   public void setActualWork (Duration val)
   {
      set (ACTUAL_WORK, val);
   }

   /**
    * Retrieves the Actual Work field contains the amount of work that has
    * already been done for all assignments assigned to a resource.
    *
    * @return Actual work value
    */
   public Duration getActualWork ()
   {
      return ((Duration)get(ACTUAL_WORK));
   }

   /**
    * Sets the amount of overtime work.
    *
    * @param overtimeWork overtime work
    */
   public void setOvertimeWork (Duration overtimeWork)
   {
      set (OVERTIME_WORK, overtimeWork);
   }

   /**
    * Retrieves the amount of overtime work.
    *
    * @return overtime work
    */
   public Duration getOvertimeWork ()
   {
      return ((Duration)get(OVERTIME_WORK));
   }

   /**
    * This field is ignored on import into MS Project.
    *
    * @param val - value to be set
    */
   public void setRemainingWork (Duration val)
   {
      set (REMAINING_WORK, val);
   }

   /**
    * Gets Remaining Work field value.
    *
    * @return value
    */
   public Duration getRemainingWork ()
   {
      return ((Duration)get(REMAINING_WORK));
   }

   /**
    * Retrieve the value of the actual overtime work field.
    *
    * @return actual overtime work value
    */
   public Duration getActualOvertimeWork ()
   {
      return (m_actualOvertimeWork);
   }

   /**
    * Sets the value of the actual overtime work field.
    *
    * @param duration actual overtime work value
    */
   public void setActualOvertimeWork (Duration duration)
   {
      m_actualOvertimeWork = duration;
   }

   /**
    * Retrieve the value of the remaining overtime work field.
    *
    * @return remaining overtime work value
    */
   public Duration getRemainingOvertimeWork ()
   {
      return (m_remainingOvertimeWork);
   }

   /**
    * Sets the value of the remaining overtime work field.
    *
    * @param duration remaining overtime work value
    */
   public void setRemainingOvertimeWork (Duration duration)
   {
      m_remainingOvertimeWork = duration;
   }

   /**
    * Sets the value of the percent work complete field.
    *
    * @param percentWorkComplete percent work complete
    */
   public void setPercentWorkComplete (Number percentWorkComplete)
   {
      set (PERCENT_WORK_COMPLETE, percentWorkComplete);
   }

   /**
    * Retrieves the value of the percent work complete field.
    *
    * @return percent work complete
    */
   public Number getPercentWorkComplete ()
   {
      return ((Number)get(PERCENT_WORK_COMPLETE));
   }

   /**
    * Sets standard rate for this resource.
    *
    * @param val value
    */
   public void setStandardRate (Rate val)
   {
      set (STANDARD_RATE, val);
   }

   /**
    * Gets Standard Rate field value.
    *
    * @return Rate
    */
   public Rate getStandardRate ()
   {
      return ((Rate)get(STANDARD_RATE));
   }

   /**
    * Sets the format of the standard rate.
    *
    * @param format standard rate format
    */
   public void setStandardRateFormat (TimeUnit format)
   {
      m_standardRateFormat = format;
   }

   /**
    * Retrieves the format of the standard rate.
    *
    * @return standard rate format
    */
   public TimeUnit getStandardRateFormat ()
   {
      return (m_standardRateFormat);
   }

   /**
    * Sets the cost field value.
    *
    * @param cost cost field value
    */
   public void setCost (Number cost)
   {
      set (COST, cost);
   }

   /**
    * Retrieves the cost field value.
    *
    * @return cost field value
    */
   public Number getCost ()
   {
      return ((Number)get(COST));
   }

   /**
    * Sets the overtime rate for this resource.
    *
    * @param overtimeRate overtime rate value
    */
   public void setOvertimeRate (Rate overtimeRate)
   {
      set (OVERTIME_RATE, overtimeRate);
   }

   /**
    * Retrieves the overtime rate for this resource.
    *
    * @return overtime rate
    */
   public Rate getOvertimeRate ()
   {
      return ((Rate)get(OVERTIME_RATE));
   }

   /**
    * Sets the format of the overtime rate.
    *
    * @param format overtime rate format
    */
   public void setOvertimeRateFormat (TimeUnit format)
   {
      m_overtimeRateFormat = format;
   }

   /**
    * Retrieves the format of the overtime rate.
    *
    * @return overtime rate format
    */
   public TimeUnit getOvertimeRateFormat ()
   {
      return (m_overtimeRateFormat);
   }

   /**
    * Retrieve the value of the overtime cost field.
    *
    * @return Overtime cost value
    */
   public Number getOvertimeCost()
   {
      return (m_overtimeCost);
   }

   /**
    * Set the value of the overtime cost field.
    *
    * @param currency Overtime cost
    */
   public void setOvertimeCost (Number currency)
   {
      m_overtimeCost = currency;
   }

   /**
    * Set the cost per use.
    *
    * @param costPerUse cost per use
    */
   public void setCostPerUse (Number costPerUse)
   {
      set (COST_PER_USE, costPerUse);
   }

   /**
    * Retrieve the cost per use.
    *
    * @return cost per use
    */
   public Number getCostPerUse ()
   {
      return ((Number)get(COST_PER_USE));
   }

   /**
    * Set the actual cost for the work already performed by this resource.
    *
    * @param actualCost actual cost
    */
   public void setActualCost (Number actualCost)
   {
      set (ACTUAL_COST, actualCost);
   }

   /**
    * Retrieves the actual cost for the work already performed by this resource.
    *
    * @return actual cost
    */
   public Number getActualCost ()
   {
      return ((Number)get(ACTUAL_COST));
   }

   /**
    * Retrieve actual overtime cost.
    *
    * @return actual overtime cost
    */
   public Number getActualOvertimeCost()
   {
      return (m_actualOvertimeCost);
   }

   /**
    * Sets the actual overtime cost.
    *
    * @param actualOvertimeCost actual overtime cost
    */
   public void setActualOvertimeCost(Number actualOvertimeCost)
   {
      m_actualOvertimeCost = actualOvertimeCost;
   }

   /**
    * Sets the remaining cost for this resource.
    *
    * @param remainingCost remaining cost
    */
   public void setRemainingCost (Number remainingCost)
   {
      set (REMAINING_COST, remainingCost);
   }

   /**
    * Retrieves the remaining cost for this resource.
    *
    * @return remaining cost
    */
   public Number getRemainingCost ()
   {
      return ((Number)get(REMAINING_COST));
   }

   /**
    * Retrieve the remaining overtime cost.
    *
    * @return remaining overtime cost
    */
   public Number getRemainingOvertimeCost()
   {
      return (m_remainingOvertimeCost);
   }

   /**
    * Set the remaining overtime cost.
    *
    * @param remainingOvertimeCost remaining overtime cost
    */
   public void setRemainingOvertimeCost(Number remainingOvertimeCost)
   {
      m_remainingOvertimeCost = remainingOvertimeCost;
   }

   /**
    * Sets the work variance.
    *
    * @param workVariance work variance
    */
   public void setWorkVariance (Duration workVariance)
   {
      set (WORK_VARIANCE, workVariance);
   }

   /**
    * Retrieves the work variance.
    *
    * @return work variance
    */
   public Duration getWorkVariance ()
   {
      return ((Duration)get(WORK_VARIANCE));
   }

   /**
    * Sets the cost variance.
    *
    * @param costVariance cost variance
    */
   public void setCostVariance (Number costVariance)
   {
      set (COST_VARIANCE, costVariance);
   }

   /**
    * Retrieves the cost variance.
    *
    * @return cost variance
    */
   public Number getCostVariance ()
   {
      return ((Number)get(COST_VARIANCE));
   }

   /**
    * Set the schedule variance.
    *
    * @param sv schedule variance
    */
   public void setSV (Number sv)
   {
      m_sv = sv;
   }

   /**
    * Retrieve the schedule variance.
    *
    * @return schedule variance
    */
   public Number getSV ()
   {
      return (m_sv);
   }

   /**
    * Set the cost variance.
    *
    * @param cv cost variance
    */
   public void setCV (Number cv)
   {
      m_cv = cv;
   }

   /**
    * Retrieve the cost variance.
    *
    * @return cost variance
    */
   public Number getCV ()
   {
      return (m_cv);
   }

   /**
    * Set the actual cost of work performed.
    *
    * @param acwp actual cost of work performed
    */
   public void setACWP (Number acwp)
   {
      m_acwp = acwp;
   }

   /**
    * Set the actual cost of work performed.
    *
    * @return actual cost of work performed
    */
   public Number getACWP ()
   {
      return (m_acwp);
   }

   /**
    * Sets the notes text for this resource.
    *
    * @param notes notes to be added
    */
   public void setNotes (String notes)
   {
      m_notes = notes;
   }

   /**
    * Retrieves the notes text for this resource.
    *
    * @return notes text
    */
   public String getNotes ()
   {
      return (m_notes==null?"":m_notes);
   }

   /**
    * Sets the budgeted cost of work scheduled.
    *
    * @param bcws budgeted cost of work scheduled
    */
   public void setBCWS (Number bcws)
   {
      m_bcws = bcws;
   }

   /**
    * Retrieves the budgeted cost of work scheduled.
    *
    * @return budgeted cost of work scheduled
    */
   public Number getBCWS ()
   {
      return (m_bcws);
   }

   /**
    * Sets the budgeted cost of work performed.
    *
    * @param bcwp budgeted cost of work performed
    */
   public void setBCWP (Number bcwp)
   {
      m_bcwp = bcwp;
   }

   /**
    * Retrievesthe budgeted cost of work performed.
    *
    * @return budgeted cost of work performed
    */
   public Number getBCWP ()
   {
      return (m_bcwp);
   }

   /**
    * Sets the generic flag.
    *
    * @param isGeneric generic flag
    */
   public void setIsGeneric (boolean isGeneric)
   {
      m_generic = isGeneric;
   }

   /**
    * Retrieves the generic flag.
    *
    * @return generic flag
    */
   public boolean getGeneric ()
   {
      return (m_generic);
   }

   /**
    * Sets the inactive flag.
    *
    * @param isInactive inactive flag
    */
   public void setIsInactive (boolean isInactive)
   {
      m_inactive = isInactive;
   }

   /**
    * Retrieves the inactive flag.
    *
    * @return inactive flag
    */
   public boolean getInactive ()
   {
      return (m_inactive);
   }

   /**
    * Sets the active directory GUID for this resource.
    *
    * @param guid active directory GUID
    */
   public void setActveDirectoryGUID (String guid)
   {
      m_activeDirectoryGUID = guid;
   }

   /**
    * Retrieves the active directory GUID for this resource.
    *
    * @return active directory GUID
    */
   public String getActiveDirectoryGUID()
   {
      return (m_activeDirectoryGUID);
   }

   /**
    * Sets the actual overtime work protected duration.
    *
    * @param duration actual overtime work protected
    */
   public void setActualOvertimeWorkProtected (Duration duration)
   {
      m_actualOvertimeWorkProtected = duration;
   }

   /**
    * Retrieves the actual overtime work protected duration.
    *
    * @return actual overtime work protected
    */
   public Duration getActualOvertimeWorkProtected ()
   {
      return (m_actualOvertimeWorkProtected);
   }

   /**
    * Sets the actual work protected duration.
    *
    * @param duration actual work protected
    */
   public void setActualWorkProtected (Duration duration)
   {
      m_actualWorkProtected = duration;
   }

   /**
    * Retrieves the actual work protected duration.
    *
    * @return actual work protected
    */
   public Duration getActualWorkProtected ()
   {
      return (m_actualWorkProtected);
   }

   /**
    * Sets the booking type.
    *
    * @param bookingType booking type
    */
   public void setBookingType (BookingType bookingType)
   {
      m_bookingType = bookingType;
   }

   /**
    * Retrieves the booking type.
    *
    * @return booking type
    */
   public BookingType getBookingType ()
   {
      return (m_bookingType);
   }

   /**
    * Sets the creation date.
    *
    * @param creationDate creation date
    */
   public void setCreationDate (Date creationDate)
   {
      m_creationDate = creationDate;
   }

   /**
    * Retrieves the creation date.
    *
    * @return creation date
    */
   public Date getCreationDate ()
   {
      return (m_creationDate);
   }

   /**
    * Sets a flag indicating that a resource is an enterprise resource.
    *
    * @param enterprise boolean flag
    */
   public void setIsEnterprise (boolean enterprise)
   {
      m_enterprise = enterprise;
   }

   /**
    * Retrieves a flag indicating that a resource is an enterprise resource.
    *
    * @return boolean flag
    */
   public boolean getEnterprise ()
   {
      return (m_enterprise);
   }

   /**
    * This method retrieves the calendar associated with this resource.
    *
    * @return ProjectCalendar instance
    */
   public ProjectCalendar getResourceCalendar ()
   {
      return (m_calendar);
   }

   /**
    * This method allows a pre-existing resource calendar
    * to be attched to a resource.
    *
    * @param calendar resource calendar
    */
   public void setResourceCalendar (ProjectCalendar calendar)
   {
      m_calendar = calendar;
      if (calendar != null)
      {
         calendar.setResource(this);
      }
   }

   /**
    * This method allows a resource calendar to be added to a resource.
    *
    * @return ResourceCalendar
    * @throws MPXJException if more than one calendar is added
    */
   public ProjectCalendar addResourceCalendar ()
      throws MPXJException
   {
      if (m_calendar != null)
      {
         throw new MPXJException (MPXJException.MAXIMUM_RECORDS);
      }

      m_calendar = new ProjectCalendar(getParentFile(), false);
      m_calendar.setResource(this);
      return (m_calendar);
   }

   /**
    * This method is used to set the value of a field in the resource,
    * and also to ensure that the field exists in the resource model
    * record.
    *
    * @param field field to be added or updated.
    * @param val new value for field.
    */
   public void set (int field, Object val)
   {
      put (field, val);
   }

   /**
    * This method is used to set the value of a field in the resource.
    *
    * @param field field to be added or updated.
    * @param val new value for field.
    */
   private void set (int field, boolean val)
   {
      put (field, val);
   }

   /**
    * Sets the Base Calendar field indicates which calendar is the base
    * calendar for a resource calendar.
    * The list includes the three built-in calendars, as well as any new base
    * calendars you have
    * created in the Change Working Time dialog box.
    *
    * @param val calendar name
    */
   public void setBaseCalendar (String val)
   {
      set (BASE_CALENDAR,val==null||val.length()==0?"Standard":val);
   }

   /**
    * Sets the baseline cost.
    * This field is ignored on import into MS Project
    *
    * @param val - value to be set
    */
   public void setBaselineCost (Number val)
   {
      set (BASELINE_COST, val);
   }

   /**
    * Sets the baseline work duration.
    * This field is ignored on import into MS Project.
    *
    * @param val - value to be set
    */
   public void setBaselineWork (Duration val)
   {
      set (BASELINE_WORK, val);
   }

   /**
    * Sets ID field value.
    *
    * @param val value
    */
   public void setID (Integer val)
   {
      ProjectFile parent = getParentFile();
      Integer previous = getID();
      if (previous != null)
      {
         parent.unmapResourceID(previous);
      }
      parent.mapResourceID(val, this);

      set (ID, val);

      if (m_assignments.isEmpty() == false)
      {
         Iterator iter = m_assignments.iterator();
         while (iter.hasNext() == true)
         {
            ((ResourceAssignment)iter.next()).setResourceID(val);
         }
      }
   }

   /**
    * This field is ignored on import into MS Project.
    *
    * @param val - value to be set
    */
   public void setLinkedFields (String val)
   {
      set (LINKED_FIELDS, val);
   }

   /**
    * Set objects.
    *
    * @param val - value to be set
    */
   public void setObjects (Integer val)
   {
      set (OBJECTS, val);
   }

   /**
    * Additional text.
    *
    * @param val text to set
    */
   public void setText1 (String val)
   {
      set (TEXT1, val);
   }

   /**
    * Additional text.
    *
    * @param val text to set
    */
   public void setText2 (String val)
   {
      set (TEXT2, val);
   }

   /**
    * Additional text.
    *
    * @param val text to set
    */
   public void setText3 (String val)
   {
      set (TEXT3, val);
   }

   /**
    * Additional text.
    *
    * @param val text to set
    */
   public void setText4 (String val)
   {
      set (TEXT4, val);
   }

   /**
    * Additional text.
    *
    * @param val text to set
    */
   public void setText5 (String val)
   {
      set (TEXT5, val);
   }

   /**
    * Sets Unique ID of this resource.
    *
    * @param val Unique ID
    */
   public void setUniqueID (Integer val)
   {
      ProjectFile parent = getParentFile();
      Integer previous = getUniqueID();
      if (previous != null)
      {
         parent.unmapResourceUniqueID(previous);
      }
      parent.mapResourceUniqueID(val, this);

      set (UNIQUE_ID, val);

      if (m_assignments.isEmpty() == false)
      {
         Iterator iter = m_assignments.iterator();
         while (iter.hasNext() == true)
         {
            ((ResourceAssignment)iter.next()).setResourceUniqueID(val);
         }
      }
   }

   /**
    * Retrieves Base Calendar name associated with this resource.
    * This field indicates which calendar is the base
    * calendar for a resource calendar.
    *
    * @return Base calendar name
    */
   public String getBaseCalendar ()
   {
      return (String)get(BASE_CALENDAR);
   }

   /**
    * Retrieves the Baseline Cost value. This value is the total planned
    * cost for a resource for all assigned tasks. Baseline cost is also
    * referred to as budget at completion (BAC).
    *
    * @return Baseline cost value
    */
   public Number getBaselineCost ()
   {
      return ((Number)get(BASELINE_COST));
   }

   /**
    * Retrieves the Baseline Work value.
    *
    * @return Baseline work value
    */
   public Duration getBaselineWork ()
   {
      return ((Duration)get(BASELINE_WORK));
   }

   /**
    * Gets ID field value.
    *
    * @return value
    */
   public Integer getID ()
   {
      return ((Integer) get(ID));
   }

   /**
    * Gets Linked Fields field value.
    *
    * @return value
    */
   public String getLinkedFields ()
   {
      return ((String)get(LINKED_FIELDS));
   }

   /**
    * Gets objects field value.
    *
    * @return value
    */
   public Integer getObjects ()
   {
      return ((Integer)get (OBJECTS));
   }

   /**
    * Gets Text 1 field value.
    *
    * @return value
    */
   public String getText1 ()
   {
      return ((String)get(TEXT1));
   }

   /**
    * Gets Text 2 field value.
    *
    * @return value
    */
   public String getText2 ()
   {
      return ((String)get(TEXT2));
   }

   /**
    * Gets Text3 field value.
    *
    * @return value
    */
   public String getText3 ()
   {
      return ((String)get(TEXT3));
   }

   /**
    * Gets Text 4 field value.
    *
    * @return value
    */
   public String getText4 ()
   {
      return ((String)get(TEXT4));
   }

   /**
    * Gets Text 5 field value.
    *
    * @return value
    */
   public String getText5 ()
   {
      return ((String)get(TEXT5));
   }

   /**
    * Gets Unique ID field value.
    *
    * @return value
    */
   public Integer getUniqueID ()
   {
      return ((Integer)get (UNIQUE_ID));
   }

   /**
    * Retrieves a text value.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Text value
    */
   public String getText6()
   {
      return ((String)get(TEXT6));
   }

   /**
    * Retrieves a text value.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Text value
    */
   public String getText7()
   {
      return ((String)get(TEXT7));
   }

   /**
    * Retrieves a text value.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Text value
    */
   public String getText8()
   {
      return ((String)get(TEXT8));
   }

   /**
    * Retrieves a text value.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Text value
    */
   public String getText9()
   {
      return ((String)get(TEXT9));
   }

   /**
    * Retrieves a text value.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Text value
    */
   public String getText10()
   {
      return ((String)get(TEXT10));
   }

   /**
    * Retrieves a text value.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Text value
    */
   public String getText11()
   {
      return ((String)get(TEXT11));
   }

   /**
    * Retrieves a text value.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Text value
    */
   public String getText12()
   {
      return ((String)get(TEXT12));
   }

   /**
    * Retrieves a text value.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Text value
    */
   public String getText13()
   {
      return ((String)get(TEXT13));
   }

   /**
    * Retrieves a text value.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Text value
    */
   public String getText14()
   {
      return ((String)get(TEXT14));
   }

   /**
    * Retrieves a text value.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Text value
    */
   public String getText15()
   {
      return ((String)get(TEXT15));
   }

   /**
    * Retrieves a text value.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Text value
    */
   public String getText16()
   {
      return ((String)get(TEXT16));
   }

   /**
    * Retrieves a text value.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Text value
    */
   public String getText17()
   {
      return ((String)get(TEXT17));
   }

   /**
    * Retrieves a text value.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Text value
    */
   public String getText18()
   {
      return ((String)get(TEXT18));
   }

   /**
    * Retrieves a text value.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Text value
    */
   public String getText19()
   {
      return ((String)get(TEXT19));
   }

   /**
    * Retrieves a text value.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Text value
    */
   public String getText20()
   {
      return ((String)get(TEXT20));
   }

   /**
    * Retrieves a text value.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Text value
    */
   public String getText21()
   {
      return ((String)get(TEXT21));
   }

   /**
    * Retrieves a text value.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Text value
    */
   public String getText22()
   {
      return ((String)get(TEXT22));
   }

   /**
    * Retrieves a text value.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Text value
    */
   public String getText23()
   {
      return ((String)get(TEXT23));
   }

   /**
    * Retrieves a text value.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Text value
    */
   public String getText24()
   {
      return ((String)get(TEXT24));
   }

   /**
    * Retrieves a text value.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Text value
    */
   public String getText25()
   {
      return ((String)get(TEXT25));
   }

   /**
    * Retrieves a text value.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Text value
    */
   public String getText26()
   {
      return ((String)get(TEXT26));
   }

   /**
    * Retrieves a text value.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Text value
    */
   public String getText27()
   {
      return ((String)get(TEXT27));
   }

   /**
    * Retrieves a text value.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Text value
    */
   public String getText28()
   {
      return ((String)get(TEXT28));
   }

   /**
    * Retrieves a text value.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Text value
    */
   public String getText29()
   {
      return ((String)get(TEXT29));
   }

   /**
    * Retrieves a text value.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Text value
    */
   public String getText30()
   {
      return ((String)get(TEXT30));
   }

   /**
    * Sets a text value.
    * Note that this value is an extension to the MPX specification.
    *
    * @param string Text value
    */
   public void setText6(String string)
   {
      set (TEXT6, string);
   }

   /**
    * Sets a text value.
    * Note that this value is an extension to the MPX specification.
    *
    * @param string Text value
    */
   public void setText7(String string)
   {
      set (TEXT7, string);
   }

   /**
    * Sets a text value.
    * Note that this value is an extension to the MPX specification.
    *
    * @param string Text value
    */
   public void setText8(String string)
   {
      set (TEXT8, string);
   }

   /**
    * Sets a text value.
    * Note that this value is an extension to the MPX specification.
    *
    * @param string Text value
    */
   public void setText9(String string)
   {
      set (TEXT9, string);
   }

   /**
    * Sets a text value.
    * Note that this value is an extension to the MPX specification.
    *
    * @param string Text value
    */
   public void setText10(String string)
   {
      set (TEXT10, string);
   }

   /**
    * Sets a text value.
    * Note that this value is an extension to the MPX specification.
    *
    * @param string Text value
    */
   public void setText11(String string)
   {
      set (TEXT11, string);
   }

   /**
    * Sets a text value.
    * Note that this value is an extension to the MPX specification.
    *
    * @param string Text value
    */
   public void setText12(String string)
   {
      set (TEXT12, string);
   }

   /**
    * Sets a text value.
    * Note that this value is an extension to the MPX specification.
    *
    * @param string Text value
    */
   public void setText13(String string)
   {
      set (TEXT13, string);
   }

   /**
    * Sets a text value.
    * Note that this value is an extension to the MPX specification.
    *
    * @param string Text value
    */
   public void setText14(String string)
   {
      set (TEXT14, string);
   }

   /**
    * Sets a text value.
    * Note that this value is an extension to the MPX specification.
    *
    * @param string Text value
    */
   public void setText15(String string)
   {
      set (TEXT15, string);
   }

   /**
    * Sets a text value.
    * Note that this value is an extension to the MPX specification.
    *
    * @param string Text value
    */
   public void setText16(String string)
   {
      set (TEXT16, string);
   }

   /**
    * Sets a text value.
    * Note that this value is an extension to the MPX specification.
    *
    * @param string Text value
    */
   public void setText17(String string)
   {
      set (TEXT17, string);
   }

   /**
    * Sets a text value.
    * Note that this value is an extension to the MPX specification.
    *
    * @param string Text value
    */
   public void setText18(String string)
   {
      set (TEXT18, string);
   }

   /**
    * Sets a text value.
    * Note that this value is an extension to the MPX specification.
    *
    * @param string Text value
    */
   public void setText19(String string)
   {
      set (TEXT19, string);
   }

   /**
    * Sets a text value.
    * Note that this value is an extension to the MPX specification.
    *
    * @param string Text value
    */
   public void setText20(String string)
   {
      set (TEXT20, string);
   }

   /**
    * Sets a text value.
    * Note that this value is an extension to the MPX specification.
    *
    * @param string Text value
    */
   public void setText21(String string)
   {
      set (TEXT21, string);
   }

   /**
    * Sets a text value.
    * Note that this value is an extension to the MPX specification.
    *
    * @param string Text value
    */
   public void setText22(String string)
   {
      set (TEXT22, string);
   }

   /**
    * Sets a text value.
    * Note that this value is an extension to the MPX specification.
    *
    * @param string Text value
    */
   public void setText23(String string)
   {
      set (TEXT23, string);
   }

   /**
    * Sets a text value.
    * Note that this value is an extension to the MPX specification.
    *
    * @param string Text value
    */
   public void setText24(String string)
   {
      set (TEXT24, string);
   }

   /**
    * Sets a text value.
    * Note that this value is an extension to the MPX specification.
    *
    * @param string Text value
    */
   public void setText25(String string)
   {
      set (TEXT25, string);
   }

   /**
    * Sets a text value.
    * Note that this value is an extension to the MPX specification.
    *
    * @param string Text value
    */
   public void setText26(String string)
   {
      set (TEXT26, string);
   }

   /**
    * Sets a text value.
    * Note that this value is an extension to the MPX specification.
    *
    * @param string Text value
    */
   public void setText27(String string)
   {
      set (TEXT27, string);
   }

   /**
    * Sets a text value.
    * Note that this value is an extension to the MPX specification.
    *
    * @param string Text value
    */
   public void setText28(String string)
   {
      set (TEXT28, string);
   }

   /**
    * Sets a text value.
    * Note that this value is an extension to the MPX specification.
    *
    * @param string Text value
    */
   public void setText29(String string)
   {
      set (TEXT29, string);
   }

   /**
    * Sets a text value.
    * Note that this value is an extension to the MPX specification.
    *
    * @param string Text value
    */
   public void setText30(String string)
   {
      set (TEXT30, string);
   }

   /**
    * Retrieves a start date.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Date start date
    */
   public Date getStart1()
   {
      return ((Date)get(START1));
   }

   /**
    * Retrieves a start date.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Date start date
    */
   public Date getStart2()
   {
      return ((Date)get(START2));
   }

   /**
    * Retrieves a start date.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Date start date
    */
   public Date getStart3()
   {
      return ((Date)get(START3));
   }

   /**
    * Retrieves a start date.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Date start date
    */
   public Date getStart4()
   {
      return ((Date)get(START4));
   }

   /**
    * Retrieves a start date.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Date start date
    */
   public Date getStart5()
   {
      return ((Date)get(START5));
   }

   /**
    * Retrieves a start date.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Date start date
    */
   public Date getStart6()
   {
      return ((Date)get(START6));
   }

   /**
    * Retrieves a start date.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Date start date
    */
   public Date getStart7()
   {
      return ((Date)get(START7));
   }

   /**
    * Retrieves a start date.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Date start date
    */
   public Date getStart8()
   {
      return ((Date)get(START8));
   }

   /**
    * Retrieves a start date.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Date start date
    */
   public Date getStart9()
   {
      return ((Date)get(START9));
   }

   /**
    * Retrieves a start date.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Date start date
    */
   public Date getStart10()
   {
      return ((Date)get(START10));
   }

   /**
    * Sets a start date.
    * Note that this value is an extension to the MPX specification.
    *
    * @param date Start date
    */
   public void setStart1(Date date)
   {
      set (START1, date);
   }

   /**
    * Sets a start date.
    * Note that this value is an extension to the MPX specification.
    *
    * @param date Start date
    */
   public void setStart2(Date date)
   {
      set (START2, date);
   }

   /**
    * Sets a start date.
    * Note that this value is an extension to the MPX specification.
    *
    * @param date Start date
    */
   public void setStart3(Date date)
   {
      set (START3, date);
   }

   /**
    * Sets a start date.
    * Note that this value is an extension to the MPX specification.
    *
    * @param date Start date
    */
   public void setStart4(Date date)
   {
      set (START4, date);
   }

   /**
    * Sets a start date.
    * Note that this value is an extension to the MPX specification.
    *
    * @param date Start date
    */
   public void setStart5(Date date)
   {
      set (START5, date);
   }

   /**
    * Sets a start date.
    * Note that this value is an extension to the MPX specification.
    *
    * @param date Start date
    */
   public void setStart6(Date date)
   {
      set (START6, date);
   }

   /**
    * Sets a start date.
    * Note that this value is an extension to the MPX specification.
    *
    * @param date Start date
    */
   public void setStart7(Date date)
   {
      set (START7, date);
   }

   /**
    * Sets a start date.
    * Note that this value is an extension to the MPX specification.
    *
    * @param date Start date
    */
   public void setStart8(Date date)
   {
      set (START8, date);
   }

   /**
    * Sets a start date.
    * Note that this value is an extension to the MPX specification.
    *
    * @param date Start date
    */
   public void setStart9(Date date)
   {
      set (START9, date);
   }

   /**
    * Sets a start date.
    * Note that this value is an extension to the MPX specification.
    *
    * @param date Start date
    */
   public void setStart10(Date date)
   {
      set (START10, date);
   }

   /**
    * Retrieves a finish date.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Date finish date
    */
   public Date getFinish1()
   {
      return ((Date)get(FINISH1));
   }

   /**
    * Retrieves a finish date.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Date finish date
    */
   public Date getFinish2()
   {
      return ((Date)get(FINISH2));
   }

   /**
    * Retrieves a finish date.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Date finish date
    */
   public Date getFinish3()
   {
      return ((Date)get(FINISH3));
   }

   /**
    * Retrieves a finish date.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Date finish date
    */
   public Date getFinish4()
   {
      return ((Date)get(FINISH4));
   }

   /**
    * Retrieves a finish date.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Date finish date
    */
   public Date getFinish5()
   {
      return ((Date)get(FINISH5));
   }

   /**
    * Retrieves a finish date.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Date finish date
    */
   public Date getFinish6()
   {
      return ((Date)get(FINISH6));
   }

   /**
    * Retrieves a finish date.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Date finish date
    */
   public Date getFinish7()
   {
      return ((Date)get(FINISH7));
   }

   /**
    * Retrieves a finish date.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Date finish date
    */
   public Date getFinish8()
   {
      return ((Date)get(FINISH8));
   }

   /**
    * Retrieves a finish date.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Date finish date
    */
   public Date getFinish9()
   {
      return ((Date)get(FINISH9));
   }

   /**
    * Retrieves a finish date.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Date finish date
    */
   public Date getFinish10()
   {
      return ((Date)get(FINISH10));
   }

   /**
    * Sets a finish date.
    * Note that this value is an extension to the MPX specification.
    *
    * @param date Finish date
    */
   public void setFinish1(Date date)
   {
      set (FINISH1, date);
   }

   /**
    * Sets a finish date.
    * Note that this value is an extension to the MPX specification.
    *
    * @param date Finish date
    */
   public void setFinish2(Date date)
   {
      set (FINISH2, date);
   }

   /**
    * Sets a finish date.
    * Note that this value is an extension to the MPX specification.
    *
    * @param date Finish date
    */
   public void setFinish3(Date date)
   {
      set (FINISH3, date);
   }

   /**
    * Sets a finish date.
    * Note that this value is an extension to the MPX specification.
    *
    * @param date Finish date
    */
   public void setFinish4(Date date)
   {
      set (FINISH4, date);
   }

   /**
    * Sets a finish date.
    * Note that this value is an extension to the MPX specification.
    *
    * @param date Finish date
    */
   public void setFinish5(Date date)
   {
      set (FINISH5, date);
   }

   /**
    * Sets a finish date.
    * Note that this value is an extension to the MPX specification.
    *
    * @param date Finish date
    */
   public void setFinish6(Date date)
   {
      set (FINISH6, date);
   }

   /**
    * Sets a finish date.
    * Note that this value is an extension to the MPX specification.
    *
    * @param date Finish date
    */
   public void setFinish7(Date date)
   {
      set (FINISH7, date);
   }

   /**
    * Sets a finish date.
    * Note that this value is an extension to the MPX specification.
    *
    * @param date Finish date
    */
   public void setFinish8(Date date)
   {
      set (FINISH8, date);
   }

   /**
    * Sets a finish date.
    * Note that this value is an extension to the MPX specification.
    *
    * @param date Finish date
    */
   public void setFinish9(Date date)
   {
      set (FINISH9, date);
   }

   /**
    * Sets a finish date.
    * Note that this value is an extension to the MPX specification.
    *
    * @param date Finish date
    */
   public void setFinish10(Date date)
   {
      set (FINISH10, date);
   }

   /**
    * Sets a numeric value.
    * Note that this value is an extension to the MPX specification.
    *
    * @param val Numeric value
    */
   public void setNumber1 (Number val)
   {
      set (NUMBER1, val);
   }

   /**
    * Retrieves a numeric value
    * Note that this value is an extension to the MPX specification.
    *
    * @return Numeric value
    */
   public Number getNumber1 ()
   {
      return ((Number)get (NUMBER1));
   }


   /**
    * Sets a numeric value.
    * Note that this value is an extension to the MPX specification.
    *
    * @param val Numeric value
    */
   public void setNumber2 (Number val)
   {
      set (NUMBER2, val);
   }

   /**
    * Retrieves a numeric value
    * Note that this value is an extension to the MPX specification.
    *
    * @return Numeric value
    */
   public Number getNumber2 ()
   {
      return ((Number)get (NUMBER2));
   }


   /**
    * Sets a numeric value.
    * Note that this value is an extension to the MPX specification.
    *
    * @param val Numeric value
    */
   public void setNumber3 (Number val)
   {
      set (NUMBER3, val);
   }

   /**
    * Retrieves a numeric value
    * Note that this value is an extension to the MPX specification.
    *
    * @return Numeric value
    */
   public Number getNumber3 ()
   {
      return ((Number)get (NUMBER3));
   }


   /**
    * Sets a numeric value.
    * Note that this value is an extension to the MPX specification.
    *
    * @param val Numeric value
    */
   public void setNumber4 (Number val)
   {
      set (NUMBER4, val);
   }

   /**
    * Retrieves a numeric value.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Numeric value
    */
   public Number getNumber4 ()
   {
      return ((Number)get (NUMBER14));
   }


   /**
    * Sets a numeric value.
    * Note that this value is an extension to the MPX specification.
    *
    * @param val Numeric value
    */
   public void setNumber5 (Number val)
   {
      set (NUMBER5, val);
   }

   /**
    * Retrieves a numeric value.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Numeric value
    */
   public Number getNumber5 ()
   {
      return ((Number)get (NUMBER5));
   }


   /**
    * Sets a numeric value.
    * Note that this value is an extension to the MPX specification.
    *
    * @param val Numeric value
    */
   public void setNumber6 (Number val)
   {
      set (NUMBER6, val);
   }

   /**
    * Retrieves a numeric value.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Numeric value
    */
   public Number getNumber6 ()
   {
      return ((Number)get (NUMBER6));
   }

   /**
    * Sets a numeric value.
    * Note that this value is an extension to the MPX specification.
    *
    * @param val Numeric value
    */
   public void setNumber7 (Number val)
   {
      set (NUMBER7, val);
   }

   /**
    * Retrieves a numeric value.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Numeric value
    */
   public Number getNumber7 ()
   {
      return ((Number)get (NUMBER7));
   }

   /**
    * Sets a numeric value.
    * Note that this value is an extension to the MPX specification.
    *
    * @param val Numeric value
    */
   public void setNumber8 (Number val)
   {
      set (NUMBER8, val);
   }

   /**
    * Retrieves a numeric value.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Numeric value
    */
   public Number getNumber8 ()
   {
      return ((Number)get (NUMBER8));
   }

   /**
    * Sets a numeric value.
    * Note that this value is an extension to the MPX specification.
    *
    * @param val Numeric value
    */
   public void setNumber9 (Number val)
   {
      set (NUMBER9, val);
   }

   /**
    * Retrieves a numeric value.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Numeric value
    */
   public Number getNumber9 ()
   {
      return ((Number)get (NUMBER9));
   }

   /**
    * Sets a numeric value.
    * Note that this value is an extension to the MPX specification.
    *
    * @param val Numeric value
    */
   public void setNumber10 (Number val)
   {
      set (NUMBER10, val);
   }

   /**
    * Retrieves a numeric value.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Numeric value
    */
   public Number getNumber10 ()
   {
      return ((Number)get (NUMBER10));
   }

   /**
    * Sets a numeric value.
    * Note that this value is an extension to the MPX specification.
    *
    * @param val Numeric value
    */
   public void setNumber11 (Number val)
   {
      set (NUMBER11, val);
   }

   /**
    * Retrieves a numeric value.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Numeric value
    */
   public Number getNumber11 ()
   {
      return ((Number)get (NUMBER11));
   }

   /**
    * Sets a numeric value.
    * Note that this value is an extension to the MPX specification.
    *
    * @param val Numeric value
    */
   public void setNumber12 (Number val)
   {
      set (NUMBER12, val);
   }

   /**
    * Retrieves a numeric value.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Numeric value
    */
   public Number getNumber12 ()
   {
      return ((Number)get (NUMBER12));
   }

   /**
    * Sets a numeric value.
    * Note that this value is an extension to the MPX specification.
    *
    * @param val Numeric value
    */
   public void setNumber13 (Number val)
   {
      set (NUMBER13, val);
   }

   /**
    * Retrieves a numeric value.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Numeric value
    */
   public Number getNumber13 ()
   {
      return ((Number)get (NUMBER13));
   }

   /**
    * Sets a numeric value.
    * Note that this value is an extension to the MPX specification.
    *
    * @param val Numeric value
    */
   public void setNumber14 (Number val)
   {
      set (NUMBER14, val);
   }

   /**
    * Retrieves a numeric value.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Numeric value
    */
   public Number getNumber14 ()
   {
      return ((Number)get (NUMBER14));
   }

   /**
    * Sets a numeric value.
    * Note that this value is an extension to the MPX specification.
    *
    * @param val Numeric value
    */
   public void setNumber15 (Number val)
   {
      set (NUMBER15, val);
   }

   /**
    * Retrieves a numeric value.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Numeric value
    */
   public Number getNumber15 ()
   {
      return ((Number)get (NUMBER15));
   }

   /**
    * Sets a numeric value.
    * Note that this value is an extension to the MPX specification.
    *
    * @param val Numeric value
    */
   public void setNumber16 (Number val)
   {
      set (NUMBER16, val);
   }

   /**
    * Retrieves a numeric value.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Numeric value
    */
   public Number getNumber16 ()
   {
      return ((Number)get (NUMBER16));
   }

   /**
    * Sets a numeric value.
    * Note that this value is an extension to the MPX specification.
    *
    * @param val Numeric value
    */
   public void setNumber17 (Number val)
   {
      set (NUMBER17, val);
   }

   /**
    * Retrieves a numeric value.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Numeric value
    */
   public Number getNumber17 ()
   {
      return ((Number)get (NUMBER17));
   }

   /**
    * Sets a numeric value.
    * Note that this value is an extension to the MPX specification.
    *
    * @param val Numeric value
    */
   public void setNumber18 (Number val)
   {
      set (NUMBER18, val);
   }

   /**
    * Retrieves a numeric value.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Numeric value
    */
   public Number getNumber18 ()
   {
      return ((Number)get (NUMBER18));
   }

   /**
    * Sets a numeric value.
    * Note that this value is an extension to the MPX specification.
    *
    * @param val Numeric value
    */
   public void setNumber19 (Number val)
   {
      set (NUMBER19, val);
   }

   /**
    * Retrieves a numeric value.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Numeric value
    */
   public Number getNumber19 ()
   {
      return ((Number)get (NUMBER19));
   }

   /**
    * Sets a numeric value.
    * Note that this value is an extension to the MPX specification.
    *
    * @param val Numeric value
    */
   public void setNumber20 (Number val)
   {
      set (NUMBER20, val);
   }

   /**
    * Retrieves a numeric value.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Numeric value
    */
   public Number getNumber20 ()
   {
      return ((Number)get (NUMBER20));
   }

   /**
    * Retrieves a duration.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Duration
    */
   public Duration getDuration1()
   {
      return (Duration)get(DURATION1);
   }

   /**
    * Retrieves a duration.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Duration
    */
   public Duration getDuration2()
   {
      return (Duration)get(DURATION2);
   }

   /**
    * Retrieves a duration.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Duration
    */
   public Duration getDuration3()
   {
      return (Duration)get(DURATION3);
   }

   /**
    * Retrieves a duration.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Duration
    */
   public Duration getDuration4()
   {
      return (Duration)get(DURATION4);
   }

   /**
    * Retrieves a duration.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Duration
    */
   public Duration getDuration5()
   {
      return (Duration)get(DURATION5);
   }

   /**
    * Retrieves a duration.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Duration
    */
   public Duration getDuration6()
   {
      return (Duration)get(DURATION6);
   }

   /**
    * Retrieves a duration.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Duration
    */
   public Duration getDuration7()
   {
      return (Duration)get(DURATION7);
   }

   /**
    * Retrieves a duration.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Duration
    */
   public Duration getDuration8()
   {
      return (Duration)get(DURATION8);
   }

   /**
    * Retrieves a duration.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Duration
    */
   public Duration getDuration9()
   {
      return (Duration)get(DURATION9);
   }

   /**
    * Retrieves a duration.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Duration
    */
   public Duration getDuration10()
   {
      return (Duration)get(DURATION10);
   }

   /**
    * Sets a duration value.
    * Note that this value is an extension to the MPX specification.
    *
    * @param duration Duration value
    */
   public void setDuration1(Duration duration)
   {
      set (DURATION1, duration);
   }

   /**
    * Sets a duration value.
    * Note that this value is an extension to the MPX specification.
    *
    * @param duration Duration value
    */
   public void setDuration2(Duration duration)
   {
      set (DURATION2, duration);
   }

   /**
    * Sets a duration value.
    * Note that this value is an extension to the MPX specification.
    *
    * @param duration Duration value
    */
   public void setDuration3(Duration duration)
   {
      set (DURATION3, duration);
   }

   /**
    * Sets a duration value.
    * Note that this value is an extension to the MPX specification.
    *
    * @param duration Duration value
    */
   public void setDuration4(Duration duration)
   {
      set (DURATION4, duration);
   }

   /**
    * Sets a duration value.
    * Note that this value is an extension to the MPX specification.
    *
    * @param duration Duration value
    */
   public void setDuration5(Duration duration)
   {
      set (DURATION5, duration);
   }

   /**
    * Sets a duration value.
    * Note that this value is an extension to the MPX specification.
    *
    * @param duration Duration value
    */
   public void setDuration6(Duration duration)
   {
      set (DURATION6, duration);
   }

   /**
    * Sets a duration value.
    * Note that this value is an extension to the MPX specification.
    *
    * @param duration Duration value
    */
   public void setDuration7(Duration duration)
   {
      set (DURATION7, duration);
   }

   /**
    * Sets a duration value.
    * Note that this value is an extension to the MPX specification.
    *
    * @param duration Duration value
    */
   public void setDuration8(Duration duration)
   {
      set (DURATION8, duration);
   }

   /**
    * Sets a duration value.
    * Note that this value is an extension to the MPX specification.
    *
    * @param duration Duration value
    */
   public void setDuration9(Duration duration)
   {
      set (DURATION9, duration);
   }

   /**
    * Sets a duration value.
    * Note that this value is an extension to the MPX specification.
    *
    * @param duration Duration value
    */
   public void setDuration10(Duration duration)
   {
      set (DURATION10, duration);
   }

   /**
    * Retrieves a date value.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Date value
    */
   public Date getDate1()
   {
      return ((Date)get(DATE1));
   }

   /**
    * Retrieves a date value.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Date value
    */
   public Date getDate10()
   {
      return ((Date)get(DATE10));
   }

   /**
    * Retrieves a date value.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Date value
    */
   public Date getDate2()
   {
      return ((Date)get(DATE2));
   }

   /**
    * Retrieves a date value.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Date value
    */
   public Date getDate3()
   {
      return ((Date)get(DATE3));
   }

   /**
    * Retrieves a date value.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Date value
    */
   public Date getDate4()
   {
      return ((Date)get(DATE4));
   }

   /**
    * Retrieves a date value.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Date value
    */
   public Date getDate5()
   {
      return ((Date)get(DATE5));
   }

   /**
    * Retrieves a date value.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Date value
    */
   public Date getDate6()
   {
      return ((Date)get(DATE6));
   }

   /**
    * Retrieves a date value.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Date value
    */
   public Date getDate7()
   {
      return ((Date)get(DATE7));
   }

   /**
    * Retrieves a date value.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Date value
    */
   public Date getDate8()
   {
      return ((Date)get(DATE8));
   }

   /**
    * Retrieves a date value.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Date value
    */
   public Date getDate9()
   {
      return ((Date)get(DATE9));
   }

   /**
    * Sets a date value.
    * Note that this value is an extension to the MPX specification.
    *
    * @param date Date value
    */
   public void setDate1(Date date)
   {
      set(DATE1, date);
   }

   /**
    * Sets a date value.
    * Note that this value is an extension to the MPX specification.
    *
    * @param date Date value
    */
   public void setDate10(Date date)
   {
      set(DATE10, date);
   }

   /**
    * Sets a date value.
    * Note that this value is an extension to the MPX specification.
    *
    * @param date Date value
    */
   public void setDate2(Date date)
   {
      set(DATE2, date);
   }

   /**
    * Sets a date value.
    * Note that this value is an extension to the MPX specification.
    *
    * @param date Date value
    */
   public void setDate3(Date date)
   {
      set(DATE3, date);
   }

   /**
    * Sets a date value.
    * Note that this value is an extension to the MPX specification.
    *
    * @param date Date value
    */
   public void setDate4(Date date)
   {
      set(DATE4, date);
   }

   /**
    * Sets a date value.
    * Note that this value is an extension to the MPX specification.
    *
    * @param date Date value
    */
   public void setDate5(Date date)
   {
      set(DATE5, date);
   }

   /**
    * Sets a date value.
    * Note that this value is an extension to the MPX specification.
    *
    * @param date Date value
    */
   public void setDate6(Date date)
   {
      set(DATE6, date);
   }

   /**
    * Sets a date value.
    * Note that this value is an extension to the MPX specification.
    *
    * @param date Date value
    */
   public void setDate7(Date date)
   {
      set(DATE7, date);
   }

   /**
    * Sets a date value.
    * Note that this value is an extension to the MPX specification.
    *
    * @param date Date value
    */
   public void setDate8(Date date)
   {
      set(DATE8, date);
   }

   /**
    * Sets a date value.
    * Note that this value is an extension to the MPX specification.
    *
    * @param date Date value
    */
   public void setDate9(Date date)
   {
      set(DATE9, date);
   }

   /**
    * Retrieves a cost.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Cost value
    */
   public Number getCost1()
   {
      return ((Number)get(COST1));
   }

   /**
    * Retrieves a cost.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Cost value
    */
   public Number getCost2()
   {
      return ((Number)get(COST2));
   }

   /**
    * Retrieves a cost.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Cost value
    */
   public Number getCost3()
   {
      return ((Number)get(COST3));
   }

   /**
    * Retrieves a cost.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Cost value
    */
   public Number getCost4()
   {
      return ((Number)get(COST4));
   }

   /**
    * Retrieves a cost.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Cost value
    */
   public Number getCost5()
   {
      return ((Number)get(COST5));
   }

   /**
    * Retrieves a cost.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Cost value
    */
   public Number getCost6()
   {
      return ((Number)get(COST6));
   }

   /**
    * Retrieves a cost.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Cost value
    */
   public Number getCost7()
   {
      return ((Number)get(COST7));
   }

   /**
    * Retrieves a cost.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Cost value
    */
   public Number getCost8()
   {
      return ((Number)get(COST8));
   }

   /**
    * Retrieves a cost.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Cost value
    */
   public Number getCost9()
   {
      return ((Number)get(COST9));
   }

   /**
    * Retrieves a cost.
    * Note that this value is an extension to the MPX specification.
    *
    * @return Cost value
    */
   public Number getCost10()
   {
      return ((Number)get(COST10));
   }

   /**
    * Sets a cost value.
    * Note that this value is an extension to the MPX specification.
    *
    * @param number Cost value
    */
   public void setCost1(Number number)
   {
      set (COST1, number);
   }

   /**
    * Sets a cost value.
    * Note that this value is an extension to the MPX specification.
    *
    * @param number Cost value
    */
   public void setCost2(Number number)
   {
      set (COST2, number);
   }

   /**
    * Sets a cost value.
    * Note that this value is an extension to the MPX specification.
    *
    * @param number Cost value
    */
   public void setCost3(Number number)
   {
      set (COST3, number);
   }

   /**
    * Sets a cost value.
    * Note that this value is an extension to the MPX specification.
    *
    * @param number Cost value
    */
   public void setCost4(Number number)
   {
      set (COST4, number);
   }

   /**
    * Sets a cost value.
    * Note that this value is an extension to the MPX specification.
    *
    * @param number Cost value
    */
   public void setCost5(Number number)
   {
      set (COST5, number);
   }

   /**
    * Sets a cost value.
    * Note that this value is an extension to the MPX specification.
    *
    * @param number Cost value
    */
   public void setCost6(Number number)
   {
      set (COST6, number);
   }

   /**
    * Sets a cost value.
    * Note that this value is an extension to the MPX specification.
    *
    * @param number Cost value
    */
   public void setCost7(Number number)
   {
      set (COST7, number);
   }

   /**
    * Sets a cost value.
    * Note that this value is an extension to the MPX specification.
    *
    * @param number Cost value
    */
   public void setCost8(Number number)
   {
      set (COST8, number);
   }

   /**
    * Sets a cost value.
    * Note that this value is an extension to the MPX specification.
    *
    * @param number Cost value
    */
   public void setCost9(Number number)
   {
      set (COST9, number);
   }

   /**
    * Sets a cost value.
    * Note that this value is an extension to the MPX specification.
    *
    * @param number Cost value
    */
   public void setCost10(Number number)
   {
      set (COST10, number);
   }

   /**
    * Retrieves the flag value.
    *
    * @return flag value
    */
   public boolean getFlag1()
   {
      return (BooleanUtility.getBoolean((Boolean)get(FLAG1)));
   }

   /**
    * Retrieves the flag value.
    *
    * @return flag value
    */
   public boolean getFlag2()
   {
      return (BooleanUtility.getBoolean((Boolean)get(FLAG2)));
   }

   /**
    * Retrieves the flag value.
    *
    * @return flag value
    */
   public boolean getFlag3()
   {
      return (BooleanUtility.getBoolean((Boolean)get(FLAG3)));
   }

   /**
    * Retrieves the flag value.
    *
    * @return flag value
    */
   public boolean getFlag4()
   {
      return (BooleanUtility.getBoolean((Boolean)get(FLAG4)));
   }

   /**
    * Retrieves the flag value.
    *
    * @return flag value
    */
   public boolean getFlag5()
   {
      return (BooleanUtility.getBoolean((Boolean)get(FLAG5)));
   }

   /**
    * Retrieves the flag value.
    *
    * @return flag value
    */
   public boolean getFlag6()
   {
      return (BooleanUtility.getBoolean((Boolean)get(FLAG6)));
   }

   /**
    * Retrieves the flag value.
    *
    * @return flag value
    */
   public boolean getFlag7()
   {
      return (BooleanUtility.getBoolean((Boolean)get(FLAG7)));
   }

   /**
    * Retrieves the flag value.
    *
    * @return flag value
    */
   public boolean getFlag8()
   {
      return (BooleanUtility.getBoolean((Boolean)get(FLAG8)));
   }

   /**
    * Retrieves the flag value.
    *
    * @return flag value
    */
   public boolean getFlag9()
   {
      return (BooleanUtility.getBoolean((Boolean)get(FLAG9)));
   }

   /**
    * Retrieves the flag value.
    *
    * @return flag value
    */
   public boolean getFlag10()
   {
      return (BooleanUtility.getBoolean((Boolean)get(FLAG10)));
   }

   /**
    * Retrieves the flag value.
    *
    * @return flag value
    */
   public boolean getFlag11()
   {
      return (BooleanUtility.getBoolean((Boolean)get(FLAG11)));
   }

   /**
    * Retrieves the flag value.
    *
    * @return flag value
    */
   public boolean getFlag12()
   {
      return (BooleanUtility.getBoolean((Boolean)get(FLAG12)));
   }

   /**
    * Retrieves the flag value.
    *
    * @return flag value
    */
   public boolean getFlag13()
   {
      return (BooleanUtility.getBoolean((Boolean)get(FLAG13)));
   }

   /**
    * Retrieves the flag value.
    *
    * @return flag value
    */
   public boolean getFlag14()
   {
      return (BooleanUtility.getBoolean((Boolean)get(FLAG14)));
   }

   /**
    * Retrieves the flag value.
    *
    * @return flag value
    */
   public boolean getFlag15()
   {
      return (BooleanUtility.getBoolean((Boolean)get(FLAG15)));
   }

   /**
    * Retrieves the flag value.
    *
    * @return flag value
    */
   public boolean getFlag16()
   {
      return (BooleanUtility.getBoolean((Boolean)get(FLAG16)));
   }

   /**
    * Retrieves the flag value.
    *
    * @return flag value
    */
   public boolean getFlag17()
   {
      return (BooleanUtility.getBoolean((Boolean)get(FLAG17)));
   }

   /**
    * Retrieves the flag value.
    *
    * @return flag value
    */
   public boolean getFlag18()
   {
      return (BooleanUtility.getBoolean((Boolean)get(FLAG18)));
   }

   /**
    * Retrieves the flag value.
    *
    * @return flag value
    */
   public boolean getFlag19()
   {
      return (BooleanUtility.getBoolean((Boolean)get(FLAG19)));
   }

   /**
    * Retrieves the flag value.
    *
    * @return flag value
    */
   public boolean getFlag20()
   {
      return (BooleanUtility.getBoolean((Boolean)get(FLAG20)));
   }

   /**
    * Sets the flag value.
    *
    * @param b flag value
    */
   public void setFlag1(boolean b)
   {
      set (FLAG1, b);
   }

   /**
    * Sets the flag value.
    *
    * @param b flag value
    */
   public void setFlag2(boolean b)
   {
      set (FLAG2, b);
   }

   /**
    * Sets the flag value.
    *
    * @param b flag value
    */
   public void setFlag3(boolean b)
   {
      set (FLAG3, b);
   }

   /**
    * Sets the flag value.
    *
    * @param b flag value
    */
   public void setFlag4(boolean b)
   {
      set (FLAG4, b);
   }

   /**
    * Sets the flag value.
    *
    * @param b flag value
    */
   public void setFlag5(boolean b)
   {
      set (FLAG5, b);
   }

   /**
    * Sets the flag value.
    *
    * @param b flag value
    */
   public void setFlag6(boolean b)
   {
      set (FLAG6, b);
   }

   /**
    * Sets the flag value.
    *
    * @param b flag value
    */
   public void setFlag7(boolean b)
   {
      set (FLAG7, b);
   }

   /**
    * Sets the flag value.
    *
    * @param b flag value
    */
   public void setFlag8(boolean b)
   {
      set (FLAG8, b);
   }

   /**
    * Sets the flag value.
    *
    * @param b flag value
    */
   public void setFlag9(boolean b)
   {
      set (FLAG9, b);
   }

   /**
    * Sets the flag value.
    *
    * @param b flag value
    */
   public void setFlag10(boolean b)
   {
      set (FLAG10, b);
   }

   /**
    * Sets the flag value.
    *
    * @param b flag value
    */
   public void setFlag11(boolean b)
   {
      set (FLAG11, b);
   }

   /**
    * Sets the flag value.
    *
    * @param b flag value
    */
   public void setFlag12(boolean b)
   {
      set (FLAG12, b);
   }

   /**
    * Sets the flag value.
    *
    * @param b flag value
    */
   public void setFlag13(boolean b)
   {
      set (FLAG13, b);
   }

   /**
    * Sets the flag value.
    *
    * @param b flag value
    */
   public void setFlag14(boolean b)
   {
      set (FLAG14, b);
   }

   /**
    * Sets the flag value.
    *
    * @param b flag value
    */
   public void setFlag15(boolean b)
   {
      set (FLAG15, b);
   }

   /**
    * Sets the flag value.
    *
    * @param b flag value
    */
   public void setFlag16(boolean b)
   {
      set (FLAG16, b);
   }

   /**
    * Sets the flag value.
    *
    * @param b flag value
    */
   public void setFlag17(boolean b)
   {
      set (FLAG17, b);
   }

   /**
    * Sets the flag value.
    *
    * @param b flag value
    */
   public void setFlag18(boolean b)
   {
      set (FLAG18, b);
   }

   /**
    * Sets the flag value.
    *
    * @param b flag value
    */
   public void setFlag19(boolean b)
   {
      set (FLAG19, b);
   }

   /**
    * Sets the flag value.
    *
    * @param b flag value
    */
   public void setFlag20(boolean b)
   {
      set (FLAG20, b);
   }

   /**
    * Sets the value of an outline code field.
    *
    * @param value outline code value
    */
   public void setOutlineCode1 (String value)
   {
      set (OUTLINECODE1, value);
   }

   /**
    * Retrieves the value of an outline code field.
    *
    * @return outline code value
    */
   public String getOutlineCode1 ()
   {
      return ((String)get(OUTLINECODE1));
   }

   /**
    * Sets the value of an outline code field.
    *
    * @param value outline code value
    */
   public void setOutlineCode2 (String value)
   {
      set (OUTLINECODE2, value);
   }

   /**
    * Retrieves the value of an outline code field.
    *
    * @return outline code value
    */
   public String getOutlineCode2 ()
   {
      return ((String)get(OUTLINECODE2));
   }

   /**
    * Sets the value of an outline code field.
    *
    * @param value outline code value
    */
   public void setOutlineCode3 (String value)
   {
      set (OUTLINECODE3, value);
   }

   /**
    * Retrieves the value of an outline code field.
    *
    * @return outline code value
    */
   public String getOutlineCode3 ()
   {
      return ((String)get(OUTLINECODE3));
   }

   /**
    * Sets the value of an outline code field.
    *
    * @param value outline code value
    */
   public void setOutlineCode4 (String value)
   {
      set (OUTLINECODE4, value);
   }

   /**
    * Retrieves the value of an outline code field.
    *
    * @return outline code value
    */
   public String getOutlineCode4 ()
   {
      return ((String)get(OUTLINECODE4));
   }

   /**
    * Sets the value of an outline code field.
    *
    * @param value outline code value
    */
   public void setOutlineCode5 (String value)
   {
      set (OUTLINECODE5, value);
   }

   /**
    * Retrieves the value of an outline code field.
    *
    * @return outline code value
    */
   public String getOutlineCode5 ()
   {
      return ((String)get(OUTLINECODE5));
   }

   /**
    * Sets the value of an outline code field.
    *
    * @param value outline code value
    */
   public void setOutlineCode6 (String value)
   {
      set (OUTLINECODE6, value);
   }

   /**
    * Retrieves the value of an outline code field.
    *
    * @return outline code value
    */
   public String getOutlineCode6 ()
   {
      return ((String)get(OUTLINECODE6));
   }

   /**
    * Sets the value of an outline code field.
    *
    * @param value outline code value
    */
   public void setOutlineCode7 (String value)
   {
      set (OUTLINECODE7, value);
   }

   /**
    * Retrieves the value of an outline code field.
    *
    * @return outline code value
    */
   public String getOutlineCode7 ()
   {
      return ((String)get(OUTLINECODE7));
   }

   /**
    * Sets the value of an outline code field.
    *
    * @param value outline code value
    */
   public void setOutlineCode8 (String value)
   {
      set (OUTLINECODE8, value);
   }

   /**
    * Retrieves the value of an outline code field.
    *
    * @return outline code value
    */
   public String getOutlineCode8 ()
   {
      return ((String)get(OUTLINECODE8));
   }

   /**
    * Sets the value of an outline code field.
    *
    * @param value outline code value
    */
   public void setOutlineCode9 (String value)
   {
      set (OUTLINECODE9, value);
   }

   /**
    * Retrieves the value of an outline code field.
    *
    * @return outline code value
    */
   public String getOutlineCode9 ()
   {
      return ((String)get(OUTLINECODE9));
   }

   /**
    * Sets the value of an outline code field.
    *
    * @param value outline code value
    */
   public void setOutlineCode10 (String value)
   {
      set (OUTLINECODE10, value);
   }

   /**
    * Retrieves the value of an outline code field.
    *
    * @return outline code value
    */
   public String getOutlineCode10 ()
   {
      return ((String)get(OUTLINECODE10));
   }

   /**
    * Removes this resource from the project.
    */
   public void remove ()
   {
      getParentFile().removeResource(this);
   }

   /**
    * Retrieve the value of a field using its alias.
    *
    * @param alias field alias
    * @return field value
    */
   public Object getFieldByAlias (String alias)
   {
      Object result = null;

      int field = getParentFile().getAliasResourceField(alias);

      if (field != -1)
      {
         result = get(field);
      }

      return (result);
   }

   /**
    * Set the value of a field using its alias.
    *
    * @param alias field alias
    * @param value field value
    */
   public void setFieldByAlias (String alias, Object value)
   {
      int field = getParentFile().getAliasResourceField(alias);

      if (field != -1)
      {
         set (field, value);
      }
   }

   /**
    * This method is used internally within MPXJ to track tasks which
    * are assigned to a particular resource.
    *
    * @param assignment resource assignment instance
    */
   public void addResourceAssignment (ResourceAssignment assignment)
   {
      m_assignments.add(assignment);
   }

   /**
    * Internal method used as part of the process of removing a
    * resource assignment.
    *
    * @param assignment resource assignment to be removed
    */
   void removeResourceAssignment (ResourceAssignment assignment)
   {
      m_assignments.remove(assignment);
   }

   /**
    * Retrieve a list of tasks assigned to this resource. Note that if this
    * project data has been read from an MPX file which declared some or all
    * of the resources assignments before the tasks and resources to which
    * the assignments relate, then these assignments may not appear in this
    * list. Caveat emptor!
    *
    * @return list of tasks assigned to this resource
    */
   public List getTaskAssignments ()
   {
      return (m_assignments);
   }

   /**
    * Where a resource in an MPP file represents a resource from a subproject,
    * this value will be non-zero. The value itself is the unique ID
    * value shown in the parent project. To retrieve the value of the
    * resource unique ID in the child project, remove the top two bytes:
    *
    * resourceID = (subprojectUniqueID & 0xFFFF)
    *
    * @return sub project unique resource ID
    */
   public Integer getSubprojectResourceUniqueID ()
   {
      return (m_subprojectResourceUniqueID);
   }

   /**
    * Sets the sub project unique resource ID.
    *
    * @param subprojectUniqueResourceID subproject unique resource ID
    */
   public void setSubprojectResourceUniqueID (Integer subprojectUniqueResourceID)
   {
      m_subprojectResourceUniqueID = subprojectUniqueResourceID;
   }

   /**
    * This method inserts a name value pair into internal storage.
    *
    * @param key attribute identifier
    * @param value attribute value
    */
   public void put (int key, Object value)
   {
      m_array[key] = value;
   }

   /**
    * Given an attribute id, this method retrieves that attribute
    * value from internal storage.
    *
    * @param key name of requested field value
    * @return requested value
    */
   public Object get (int key)
   {
      return (m_array[key]);
   }

   /**
    * This method inserts a name value pair into internal storage.
    *
    * @param key attribute identifier
    * @param value attribute value
    */
   private void put (int key, boolean value)
   {
      put (key, (value==true ? Boolean.TRUE : Boolean.FALSE));
   }

   /**
    * This method implements the only method in the Comparable interface.
    * This allows Resources to be compared and sorted based on their ID value.
    * Note that if the MPX/MPP file has been generated by MSP, the ID value
    * will always be in the correct sequence. The Unique ID value will not
    * necessarily be in the correct sequence as task insertions and deletions
    * will change the order.
    *
    * @param o object to compare this instance with
    * @return result of comparison
    */
   public int compareTo (Object o)
   {
      int id1 = NumberUtility.getInt(getID());
      int id2 = NumberUtility.getInt(((Resource)o).getID());
      return ((id1 < id2) ? (-1) : ((id1 == id2) ? 0 : 1));
   }

   /**
    * Array of field values.
    */
   private Object[] m_array = new Object[MAX_FIELDS + MAX_EXTENDED_FIELDS];

   /**
    * Resource calendar for this resource.
    */
   private ProjectCalendar m_calendar;

   /**
    * Resource notes for this resource.
    */
   private String m_notes;

   /**
    * List of all assignments for this resource.
    */
   private List m_assignments = new LinkedList();

   /**
    * The following member variables are extended attributes. They are
    * do not form part of the MPX file format definition, and are neither
    * loaded from an MPX file, or saved to an MPX file. Their purpose
    * is to provide storage for attributes which are defined by later versions
    * of Microsoft Project. This allows these attributes to be manipulated
    * when they have been retrieved from file formats other than MPX.
    */
   private Duration m_regularWork;
   private Number m_overtimeCost;
   private Number m_actualOvertimeCost;
   private Number m_remainingOvertimeCost;
   private Date m_availableFrom;
   private Date m_availableTo;
   private ResourceType m_type;
   private boolean m_null;
   private String m_phonetics;
   private String m_ntAccount;
   private String m_materialLabel;
   private WorkGroup m_workGroup;
   private String m_hyperlink;
   private String m_hyperlinkAddress;
   private String m_hyperlinkSubAddress;
   private Date m_start;
   private Date m_finish;
   private boolean m_canLevel;
   private Duration m_actualOvertimeWork;
   private Duration m_remainingOvertimeWork;
   private TimeUnit m_standardRateFormat;
   private TimeUnit m_overtimeRateFormat;
   private Number m_sv;
   private Number m_cv;
   private Number m_acwp;
   private Number m_bcws;
   private Number m_bcwp;
   private boolean m_generic;
   private boolean m_inactive;
   private String m_activeDirectoryGUID;
   private Duration m_actualOvertimeWorkProtected;
   private Duration m_actualWorkProtected;
   private BookingType m_bookingType;
   private Date m_creationDate;
   private boolean m_enterprise;
   private Integer m_subprojectResourceUniqueID;

   /**
    * The % Work Complete field contains the current status of all tasks
    * assigned to a resource,
    * expressed as the total percentage of the resource's work that has
    * been completed.
    */
   public static final int PERCENT_WORK_COMPLETE = 26;

   /**
    * The Accrue At field provides choices for how and when resource
    * standard and overtime costs
    * are to be charged, or accrued, to the cost of a task. The options are:
    * - Start
    * - End
    * - Prorated (default)
    */
   public static final int ACCRUE_AT = 45;

   /**
    * The Actual Cost field shows the sum of costs incurred for the work
    * already performed
    * by a resource for all assigned tasks.
    */
   public static final int ACTUAL_COST = 32;

   /**
    * The Actual Work field contains the amount of work that has already
    * been done for all
    * assignments assigned to a resource.
    */
   public static final int ACTUAL_WORK = 22;

   /**
    * The Base Calendar field indicates which calendar is the base calendar
    * for a resource calendar.
    * The list includes the three built-in calendars, as well as any new base
    * calendars you have
    * created in the Change Working Time dialog box.
    */
   public static final int BASE_CALENDAR = 48;

   /**
    * The Baseline Cost field shows the total planned cost for a resource
    * for all assigned tasks.
    * Baseline cost is also referred to as budget at completion (BAC).
    */
   public static final int BASELINE_COST = 31;

   /**
    * The Baseline Work field shows the originally planned amount of work to
    * be performed for all
    * assignments assigned to a resource. This field shows the planned
    * person-hours scheduled for
    * a resource. Information in the Baseline Work field becomes available
    * when you set a baseline
    * for the project.
    */
   public static final int BASELINE_WORK = 21;

   /**
    * The Code field contains any code, abbreviation, or number you want to
    * enter as part of a
    * resource's information.
    */
   public static final int CODE = 4;

   /**
    * The Cost field shows the total scheduled cost for a resource for all
    * assigned tasks.
    *  Cost is based on costs already incurred for work performed by the
    *  resource on all
    * assigned tasks, in addition to the costs planned for the remaining work.
    */
   public static final int COST = 30;

   /**
    * The Cost Per Use field shows the cost that accrues each time a
    * resource is used.
    */
   public static final int COST_PER_USE = 44;

   /**
    * The Cost Variance field shows the difference between the baseline cost
    * and total cost for
    * a resource. This is also referred to as variance at completion (VAC).
    */
   public static final int COST_VARIANCE = 34;

   /**
    * The Email Address field contains the e-mail address of a resource.
    * If the Email Address
    * field is blank, Microsoft Project uses the name in the Name field as
    * the e-mail address.
    */
   public static final int EMAIL_ADDRESS = 11;

   /**
    * The Group field contains the name of the group to which
    * a resource belongs.
    */
   public static final int GROUP = 3;

   /**
    * The ID field contains the identifier number that Microsoft Project
    * automatically assigns
    * to each resource. The ID indicates the position of a resource in
    * relation to the other resources.
    */
   public static final int ID = 40;

   /**
    * The Initials field shows the abbreviation for a resource name.
    */
   public static final int INITIALS = 2;

   /**
    * The Linked Fields field indicates whether there are OLE links
    * to the resource,
    * either from elsewhere in the active project, another Microsoft Project
    * file, or from another program.
    */
   public static final int LINKED_FIELDS = 51;

   /**
    * The Max Units field contains the maximum percentage or number of units
    * representing the maximum
    * capacity for which a resource is available to accomplish any tasks.
    * The default for the Max Units
    * field is 100 percent.
    */
   public static final int MAX_UNITS = 41;

   /**
    * The Name field contains the name of a resource.
    */
   public static final int NAME = 1;

   /**
    * The Notes field contains notes that you can enter about a resource.
    * You can use resource
    * notes to help maintain information about a resource.
    */
   //public static final int NOTES = 10;

   /**
    * The Objects field contains the number of objects associated
    * with a resource.
    */
   public static final int OBJECTS = 50;

   /**
    * The Overallocated field indicates whether a resource is assigned to do
    * more work on
    * all assigned tasks than can be done within the resource's
    * normal work capacity.
    */
   public static final int OVERALLOCATED = 46;

   /**
    * The Overtime Rate field shows the rate of pay for overtime work
    * performed by a resource.
    */
   public static final int OVERTIME_RATE = 43;

   /**
    * The Overtime Work field contains the amount of overtime to be
    * performed for all
    * tasks assigned to a resource and charged at the resource's overtime rate.
    */
   public static final int OVERTIME_WORK = 24;

   /**
    * The Peak field contains the maximum percentage or number of units
    * for which a resource
    * is assigned at any one time for all tasks assigned to the resource.
    */
   public static final int PEAK_UNITS = 47;

   /**
    * The Remaining Cost field shows the remaining scheduled expense that
    * will be incurred
    * in completing the remaining work assigned to a resource.
    * This applies to all work
    * assigned to the resource for all assigned tasks.
    */
   public static final int REMAINING_COST = 33;

   /**
    * The Remaining Work field contains the amount of time, or person-hours,
    * still required by a resource to complete all assigned tasks.
    */
   public static final int REMAINING_WORK = 23;

   /**
    * The Standard Rate field shows the rate of pay for regular, nonovertime
    * work performed by a resource.
    */
   public static final int STANDARD_RATE = 42;

   /**
    * The Text fields show any custom text information you want to enter in your
    * project regarding resources.
    */
   public static final int TEXT1 = 5;

   /**
    * The Text fields show any custom text information you want to enter in your
    * project regarding resources.
    */
   public static final int TEXT2 = 6;

   /**
    * The Text fields show any custom text information you want to enter in your
    * project regarding resources.
    */
   public static final int TEXT3 = 7;

   /**
    * The Text fields show any custom text information you want to enter in your
    * project regarding resources.
    */
   public static final int TEXT4 = 8;

   /**
    * The Text fields show any custom text information you want to enter in your
    * project regarding resources.
    */
   public static final int TEXT5 = 9;

   /**
    * The Unique ID field contains the number that Microsoft Project
    * automatically
    * designates whenever a new resource is added. This number indicates
    * the sequence
    * in which the resource was added to the project, regardless of
    * placement in the sheet.
    */
   public static final int UNIQUE_ID = 49;

   /**
    * The Work field contains the total amount of work scheduled to be
    * performed by a
    * resource on all assigned tasks. This field shows the total work,
    * or person-hours, for a resource.
    */
   public static final int WORK = 20;

   /**
    * The Work Variance field contains the difference between a resource's
    * total baseline work
    * and the currently scheduled work.
    */
   public static final int WORK_VARIANCE = 25;

   /**
    * Maximum number of fields in this record. Note that this is
    * package access to allow the model to get at it.
    */
   public static final int MAX_FIELDS = 52;

   private static final int EXTENDED_OFFSET = MAX_FIELDS;

   /**
    * Maximum number of extended fields in this record.
    */
   private static final int MAX_EXTENDED_FIELDS = 125;

   /**
    * The following constants are used purely to identify custom fields,
    * these field names are NOT written to the MPX file.
    */
   public static final int TEXT6 = EXTENDED_OFFSET + 0;
   public static final int TEXT7 = EXTENDED_OFFSET + 1;
   public static final int TEXT8 = EXTENDED_OFFSET + 2;
   public static final int TEXT9 = EXTENDED_OFFSET + 3;
   public static final int TEXT10 = EXTENDED_OFFSET + 4;
   public static final int TEXT11 = EXTENDED_OFFSET + 5;
   public static final int TEXT12 = EXTENDED_OFFSET + 6;
   public static final int TEXT13 = EXTENDED_OFFSET + 7;
   public static final int TEXT14 = EXTENDED_OFFSET + 8;
   public static final int TEXT15 = EXTENDED_OFFSET + 9;
   public static final int TEXT16 = EXTENDED_OFFSET + 10;
   public static final int TEXT17 = EXTENDED_OFFSET + 11;
   public static final int TEXT18 = EXTENDED_OFFSET + 12;
   public static final int TEXT19 = EXTENDED_OFFSET + 13;
   public static final int TEXT20 = EXTENDED_OFFSET + 14;
   public static final int TEXT21 = EXTENDED_OFFSET + 15;
   public static final int TEXT22 = EXTENDED_OFFSET + 16;
   public static final int TEXT23 = EXTENDED_OFFSET + 17;
   public static final int TEXT24 = EXTENDED_OFFSET + 18;
   public static final int TEXT25 = EXTENDED_OFFSET + 19;
   public static final int TEXT26 = EXTENDED_OFFSET + 20;
   public static final int TEXT27 = EXTENDED_OFFSET + 21;
   public static final int TEXT28 = EXTENDED_OFFSET + 22;
   public static final int TEXT29 = EXTENDED_OFFSET + 23;
   public static final int TEXT30 = EXTENDED_OFFSET + 24;

   public static final int START1 = EXTENDED_OFFSET + 25;
   public static final int START2 = EXTENDED_OFFSET + 26;
   public static final int START3 = EXTENDED_OFFSET + 27;
   public static final int START4 = EXTENDED_OFFSET + 28;
   public static final int START5 = EXTENDED_OFFSET + 29;
   public static final int START6 = EXTENDED_OFFSET + 30;
   public static final int START7 = EXTENDED_OFFSET + 31;
   public static final int START8 = EXTENDED_OFFSET + 32;
   public static final int START9 = EXTENDED_OFFSET + 33;
   public static final int START10 = EXTENDED_OFFSET + 34;

   public static final int FINISH1 = EXTENDED_OFFSET + 35;
   public static final int FINISH2 = EXTENDED_OFFSET + 36;
   public static final int FINISH3 = EXTENDED_OFFSET + 37;
   public static final int FINISH4 = EXTENDED_OFFSET + 38;
   public static final int FINISH5 = EXTENDED_OFFSET + 39;
   public static final int FINISH6 = EXTENDED_OFFSET + 40;
   public static final int FINISH7 = EXTENDED_OFFSET + 41;
   public static final int FINISH8 = EXTENDED_OFFSET + 42;
   public static final int FINISH9 = EXTENDED_OFFSET + 43;
   public static final int FINISH10 = EXTENDED_OFFSET + 44;

   public static final int COST1 = EXTENDED_OFFSET + 45;
   public static final int COST2 = EXTENDED_OFFSET + 46;
   public static final int COST3 = EXTENDED_OFFSET + 47;
   public static final int COST4 = EXTENDED_OFFSET + 48;
   public static final int COST5 = EXTENDED_OFFSET + 49;
   public static final int COST6 = EXTENDED_OFFSET + 50;
   public static final int COST7 = EXTENDED_OFFSET + 51;
   public static final int COST8 = EXTENDED_OFFSET + 52;
   public static final int COST9 = EXTENDED_OFFSET + 53;
   public static final int COST10 = EXTENDED_OFFSET + 54;

   public static final int DATE1 = EXTENDED_OFFSET + 55;
   public static final int DATE2 = EXTENDED_OFFSET + 56;
   public static final int DATE3 = EXTENDED_OFFSET + 57;
   public static final int DATE4 = EXTENDED_OFFSET + 58;
   public static final int DATE5 = EXTENDED_OFFSET + 59;
   public static final int DATE6 = EXTENDED_OFFSET + 60;
   public static final int DATE7 = EXTENDED_OFFSET + 61;
   public static final int DATE8 = EXTENDED_OFFSET + 62;
   public static final int DATE9 = EXTENDED_OFFSET + 63;
   public static final int DATE10 = EXTENDED_OFFSET + 64;

   public static final int FLAG1 = EXTENDED_OFFSET + 65;
   public static final int FLAG2 = EXTENDED_OFFSET + 66;
   public static final int FLAG3 = EXTENDED_OFFSET + 67;
   public static final int FLAG4 = EXTENDED_OFFSET + 68;
   public static final int FLAG5 = EXTENDED_OFFSET + 69;
   public static final int FLAG6 = EXTENDED_OFFSET + 70;
   public static final int FLAG7 = EXTENDED_OFFSET + 71;
   public static final int FLAG8 = EXTENDED_OFFSET + 72;
   public static final int FLAG9 = EXTENDED_OFFSET + 73;
   public static final int FLAG10 = EXTENDED_OFFSET + 74;
   public static final int FLAG11 = EXTENDED_OFFSET + 75;
   public static final int FLAG12 = EXTENDED_OFFSET + 76;
   public static final int FLAG13 = EXTENDED_OFFSET + 77;
   public static final int FLAG14 = EXTENDED_OFFSET + 78;
   public static final int FLAG15 = EXTENDED_OFFSET + 79;
   public static final int FLAG16 = EXTENDED_OFFSET + 80;
   public static final int FLAG17 = EXTENDED_OFFSET + 81;
   public static final int FLAG18 = EXTENDED_OFFSET + 82;
   public static final int FLAG19 = EXTENDED_OFFSET + 83;
   public static final int FLAG20 = EXTENDED_OFFSET + 84;

   public static final int NUMBER1 = EXTENDED_OFFSET + 85;
   public static final int NUMBER2 = EXTENDED_OFFSET + 86;
   public static final int NUMBER3 = EXTENDED_OFFSET + 87;
   public static final int NUMBER4 = EXTENDED_OFFSET + 88;
   public static final int NUMBER5 = EXTENDED_OFFSET + 89;
   public static final int NUMBER6 = EXTENDED_OFFSET + 90;
   public static final int NUMBER7 = EXTENDED_OFFSET + 91;
   public static final int NUMBER8 = EXTENDED_OFFSET + 92;
   public static final int NUMBER9 = EXTENDED_OFFSET + 93;
   public static final int NUMBER10 = EXTENDED_OFFSET + 94;
   public static final int NUMBER11 = EXTENDED_OFFSET + 95;
   public static final int NUMBER12 = EXTENDED_OFFSET + 96;
   public static final int NUMBER13 = EXTENDED_OFFSET + 97;
   public static final int NUMBER14 = EXTENDED_OFFSET + 98;
   public static final int NUMBER15 = EXTENDED_OFFSET + 99;
   public static final int NUMBER16 = EXTENDED_OFFSET + 100;
   public static final int NUMBER17 = EXTENDED_OFFSET + 101;
   public static final int NUMBER18 = EXTENDED_OFFSET + 102;
   public static final int NUMBER19 = EXTENDED_OFFSET + 103;
   public static final int NUMBER20 = EXTENDED_OFFSET + 104;

   public static final int DURATION1 = EXTENDED_OFFSET + 105;
   public static final int DURATION2 = EXTENDED_OFFSET + 106;
   public static final int DURATION3 = EXTENDED_OFFSET + 107;
   public static final int DURATION4 = EXTENDED_OFFSET + 108;
   public static final int DURATION5 = EXTENDED_OFFSET + 109;
   public static final int DURATION6 = EXTENDED_OFFSET + 110;
   public static final int DURATION7 = EXTENDED_OFFSET + 111;
   public static final int DURATION8 = EXTENDED_OFFSET + 112;
   public static final int DURATION9 = EXTENDED_OFFSET + 113;
   public static final int DURATION10 = EXTENDED_OFFSET + 114;

   public static final int OUTLINECODE1 = EXTENDED_OFFSET + 115;
   public static final int OUTLINECODE2 = EXTENDED_OFFSET + 116;
   public static final int OUTLINECODE3 = EXTENDED_OFFSET + 117;
   public static final int OUTLINECODE4 = EXTENDED_OFFSET + 118;
   public static final int OUTLINECODE5 = EXTENDED_OFFSET + 119;
   public static final int OUTLINECODE6 = EXTENDED_OFFSET + 120;
   public static final int OUTLINECODE7 = EXTENDED_OFFSET + 121;
   public static final int OUTLINECODE8 = EXTENDED_OFFSET + 122;
   public static final int OUTLINECODE9 = EXTENDED_OFFSET + 123;
   public static final int OUTLINECODE10 = EXTENDED_OFFSET + 124;

   public static final DataType[] FIELD_TYPES = new DataType [MAX_FIELDS + MAX_EXTENDED_FIELDS];
   static
   {
      FIELD_TYPES[COST] = DataType.CURRENCY;
      FIELD_TYPES[COST_PER_USE] = DataType.CURRENCY;
      FIELD_TYPES[ACTUAL_COST] = DataType.CURRENCY;
      FIELD_TYPES[REMAINING_COST] = DataType.CURRENCY;
      FIELD_TYPES[COST_VARIANCE] = DataType.CURRENCY;
      FIELD_TYPES[BASELINE_COST] = DataType.CURRENCY;
      FIELD_TYPES[COST1] = DataType.CURRENCY;
      FIELD_TYPES[COST2] = DataType.CURRENCY;
      FIELD_TYPES[COST3] = DataType.CURRENCY;
      FIELD_TYPES[COST4] = DataType.CURRENCY;
      FIELD_TYPES[COST5] = DataType.CURRENCY;
      FIELD_TYPES[COST6] = DataType.CURRENCY;
      FIELD_TYPES[COST7] = DataType.CURRENCY;
      FIELD_TYPES[COST8] = DataType.CURRENCY;
      FIELD_TYPES[COST9] = DataType.CURRENCY;
      FIELD_TYPES[COST10] = DataType.CURRENCY;

      FIELD_TYPES[START1] = DataType.DATE;
      FIELD_TYPES[START2] = DataType.DATE;
      FIELD_TYPES[START3] = DataType.DATE;
      FIELD_TYPES[START4] = DataType.DATE;
      FIELD_TYPES[START5] = DataType.DATE;
      FIELD_TYPES[START6] = DataType.DATE;
      FIELD_TYPES[START7] = DataType.DATE;
      FIELD_TYPES[START8] = DataType.DATE;
      FIELD_TYPES[START9] = DataType.DATE;
      FIELD_TYPES[START10] = DataType.DATE;

      FIELD_TYPES[FINISH1] = DataType.DATE;
      FIELD_TYPES[FINISH2] = DataType.DATE;
      FIELD_TYPES[FINISH3] = DataType.DATE;
      FIELD_TYPES[FINISH4] = DataType.DATE;
      FIELD_TYPES[FINISH5] = DataType.DATE;
      FIELD_TYPES[FINISH6] = DataType.DATE;
      FIELD_TYPES[FINISH7] = DataType.DATE;
      FIELD_TYPES[FINISH8] = DataType.DATE;
      FIELD_TYPES[FINISH9] = DataType.DATE;
      FIELD_TYPES[FINISH10] = DataType.DATE;

      FIELD_TYPES[DATE1] = DataType.DATE;
      FIELD_TYPES[DATE2] = DataType.DATE;
      FIELD_TYPES[DATE3] = DataType.DATE;
      FIELD_TYPES[DATE4] = DataType.DATE;
      FIELD_TYPES[DATE5] = DataType.DATE;
      FIELD_TYPES[DATE6] = DataType.DATE;
      FIELD_TYPES[DATE7] = DataType.DATE;
      FIELD_TYPES[DATE8] = DataType.DATE;
      FIELD_TYPES[DATE9] = DataType.DATE;
      FIELD_TYPES[DATE10] = DataType.DATE;

      FIELD_TYPES[MAX_UNITS] = DataType.UNITS;

      FIELD_TYPES[PEAK_UNITS] = DataType.PERCENTAGE;
      FIELD_TYPES[PERCENT_WORK_COMPLETE] = DataType.PERCENTAGE;

      FIELD_TYPES[ACCRUE_AT] = DataType.ACCRUE;

      FIELD_TYPES[WORK] = DataType.DURATION;
      FIELD_TYPES[ACTUAL_WORK] = DataType.DURATION;
      FIELD_TYPES[OVERTIME_WORK] = DataType.DURATION;
      FIELD_TYPES[REMAINING_WORK] = DataType.DURATION;
      FIELD_TYPES[WORK_VARIANCE] = DataType.DURATION;
      FIELD_TYPES[BASELINE_WORK] = DataType.DURATION;
      FIELD_TYPES[DURATION1] = DataType.DURATION;
      FIELD_TYPES[DURATION2] = DataType.DURATION;
      FIELD_TYPES[DURATION3] = DataType.DURATION;
      FIELD_TYPES[DURATION4] = DataType.DURATION;
      FIELD_TYPES[DURATION5] = DataType.DURATION;
      FIELD_TYPES[DURATION6] = DataType.DURATION;
      FIELD_TYPES[DURATION7] = DataType.DURATION;
      FIELD_TYPES[DURATION8] = DataType.DURATION;
      FIELD_TYPES[DURATION9] = DataType.DURATION;
      FIELD_TYPES[DURATION10] = DataType.DURATION;

      FIELD_TYPES[STANDARD_RATE] = DataType.RATE;
      FIELD_TYPES[OVERTIME_RATE] = DataType.RATE;
   }
}
