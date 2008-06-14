/*
 * file:       MPXWriter.java
 * author:     Jon Iles
 * copyright:  (c) Packwood Software Limited 2005
 * date:       Jan 3, 2006
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

package net.sf.mpxj.mpx;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import net.sf.mpxj.AccrueType;
import net.sf.mpxj.ConstraintType;
import net.sf.mpxj.DataType;
import net.sf.mpxj.DateRange;
import net.sf.mpxj.Duration;
import net.sf.mpxj.FileCreationRecord;
import net.sf.mpxj.Priority;
import net.sf.mpxj.ProjectCalendar;
import net.sf.mpxj.ProjectCalendarException;
import net.sf.mpxj.ProjectCalendarHours;
import net.sf.mpxj.ProjectFile;
import net.sf.mpxj.ProjectHeader;
import net.sf.mpxj.Rate;
import net.sf.mpxj.RecurringTask;
import net.sf.mpxj.Relation;
import net.sf.mpxj.RelationType;
import net.sf.mpxj.Resource;
import net.sf.mpxj.ResourceAssignment;
import net.sf.mpxj.ResourceAssignmentWorkgroupFields;
import net.sf.mpxj.ResourceField;
import net.sf.mpxj.Task;
import net.sf.mpxj.TaskField;
import net.sf.mpxj.TaskType;
import net.sf.mpxj.TimeUnit;
import net.sf.mpxj.utility.MPXJFormats;
import net.sf.mpxj.utility.NumberUtility;
import net.sf.mpxj.writer.AbstractProjectWriter;


/**
 * This class creates a new MPX file from the contents of an PorjectFile instance.
 */
public final class MPXWriter extends AbstractProjectWriter
{
   /**
    * {@inheritDoc}
    */
   public void write (ProjectFile projectFile, OutputStream out)
      throws IOException
   {
      write (projectFile, out, true);
   }

   /**
    * Writes the contents of the project as an MPX file. This method allows the
    * caller to control whether the locale defaults are used to replace
    * the settings held in the project file, or whether the settings in the
    * project file are used. This affects things lik currency symbol, date
    * formats, file delimiters and so on.
    *
    * @param projectFile project file instance
    * @param fileName file name
    * @param useLocaleDefaults boolean flag
    * @throws IOException
    */
   public void write (ProjectFile projectFile, String fileName, boolean useLocaleDefaults)
      throws IOException
   {
      FileOutputStream fos = new FileOutputStream(fileName);
      write(projectFile, fos, useLocaleDefaults);
      fos.flush();
      fos.close();
   }

   /**
    * Writes the contents of the project as an MPX file. This method allows the
    * caller to control whether the locale defaults are used to replace
    * the settings held in the project file, or whether the settings in the
    * project file are used. This affects things lik currency symbol, date
    * formats, file delimiters and so on.
    *
    * @param projectFile project file instance
    * @param file file instance
    * @param useLocaleDefaults boolean flag
    * @throws IOException
    */
   public void write (ProjectFile projectFile, File file, boolean useLocaleDefaults)
      throws IOException
   {
      FileOutputStream fos = new FileOutputStream(file);
      write(projectFile, fos, useLocaleDefaults);
      fos.flush();
      fos.close();
   }

   /**
    * Writes the contents of the project as an MPX file. This method allows the
    * caller to control whether the locale defaults are used to replace
    * the settings held in the project file, or whether the settings in the
    * project file are used. This affects things lik currency symbol, date
    * formats, file delimiters and so on.
    *
    * @param projectFile project file instance
    * @param out output stream instance
    * @param useLocaleDefaults boolean flag
    * @throws IOException
    */
   public void write (ProjectFile projectFile, OutputStream out, boolean useLocaleDefaults)
      throws IOException
   {
      m_projectFile = projectFile;
      if (useLocaleDefaults == true)
      {
         LocaleUtility.setLocale(m_projectFile, m_locale);
      }

      m_delimiter = projectFile.getDelimiter();
      m_writer = new OutputStreamWriter(new BufferedOutputStream(out), projectFile.getFileCreationRecord().getCodePage().getCharset());
      m_buffer = new StringBuffer();
      m_formats = new MPXJFormats(m_locale, LocaleData.getString(m_locale, LocaleData.NA), m_projectFile);

      try
      {
         write();
      }

      finally
      {
         m_writer = null;
         m_projectFile = null;
         m_resourceModel = null;
         m_taskModel = null;
         m_buffer = null;
         m_locale = null;
         m_formats = null;
      }
   }

   /**
    * Writes the contents of the project file as MPX records.
    *
    * @throws IOException
    */
   private void write ()
      throws IOException
   {
      writeFileCreationRecord(m_projectFile.getFileCreationRecord());           
      writeProjectHeader(m_projectFile.getProjectHeader());

      if (m_projectFile.getAllResources().isEmpty() == false)
      {
         m_resourceModel = new ResourceModel(m_projectFile, m_locale);
         m_writer.write(m_resourceModel.toString());
         for (Resource resource : m_projectFile.getAllResources())
         {
            writeResource (resource);
         }
      }
      
      if (m_projectFile.getAllTasks().isEmpty() == false)
      {
         m_taskModel = new TaskModel(m_projectFile, m_locale);
         m_writer.write(m_taskModel.toString());
         writeTasks (m_projectFile.getChildTasks());
      }
      
      m_writer.flush();
   }

   /**
    * Write file creation record.
    *
    * @param record file creation record
    * @throws IOException
    */
   private void writeFileCreationRecord (FileCreationRecord record)
      throws IOException
   {
      m_buffer.setLength(0);
      m_buffer.append("MPX");
      m_buffer.append(m_delimiter);
      m_buffer.append(record.getProgramName());
      m_buffer.append(m_delimiter);
      m_buffer.append(record.getFileVersion());
      m_buffer.append(m_delimiter);
      m_buffer.append(record.getCodePage());
      m_buffer.append(MPXConstants.EOL);
      m_writer.write(m_buffer.toString());
   }

   /**
    * Write project header.
    *
    * @param record project header
    * @throws IOException
    */
   private void writeProjectHeader(ProjectHeader record)
      throws IOException
   {
      m_buffer.setLength(0);

      //
      // Currency Settings Record
      //
      m_buffer.append (MPXConstants.CURRENCY_SETTINGS_RECORD_NUMBER);
      m_buffer.append (m_delimiter);
      m_buffer.append(format(record.getCurrencySymbol()));
      m_buffer.append (m_delimiter);
      m_buffer.append(format(record.getSymbolPosition()));
      m_buffer.append (m_delimiter);
      m_buffer.append(format(record.getCurrencyDigits()));
      m_buffer.append (m_delimiter);
      m_buffer.append(format(new Character(record.getThousandsSeparator())));
      m_buffer.append (m_delimiter);
      m_buffer.append(format(new Character(record.getDecimalSeparator())));
      stripTrailingDelimiters(m_buffer);
      m_buffer.append (MPXConstants.EOL);

      //
      // Default Settings Record
      //
      m_buffer.append (MPXConstants.DEFAULT_SETTINGS_RECORD_NUMBER);
      m_buffer.append (m_delimiter);
      m_buffer.append(format(new Integer(record.getDefaultDurationUnits().getValue())));
      m_buffer.append (m_delimiter);
      m_buffer.append(record.getDefaultDurationIsFixed()?"1":"0");
      m_buffer.append (m_delimiter);
      m_buffer.append(format(new Integer(record.getDefaultWorkUnits().getValue())));
      m_buffer.append (m_delimiter);
      m_buffer.append(format(formatDecimal(NumberUtility.getDouble(record.getMinutesPerDay())/60)));
      m_buffer.append (m_delimiter);
      m_buffer.append(format(formatDecimal(NumberUtility.getDouble(record.getMinutesPerWeek())/60)));
      m_buffer.append (m_delimiter);
      m_buffer.append(format(formatRate(record.getDefaultStandardRate())));
      m_buffer.append (m_delimiter);
      m_buffer.append(format(formatRate(record.getDefaultOvertimeRate())));
      m_buffer.append (m_delimiter);
      m_buffer.append(record.getUpdatingTaskStatusUpdatesResourceStatus()?"1":"0");
      m_buffer.append (m_delimiter);
      m_buffer.append(record.getSplitInProgressTasks()?"1":"0");
      stripTrailingDelimiters(m_buffer);
      m_buffer.append (MPXConstants.EOL);

      //
      // Date Time Settings Record
      //
      m_buffer.append (MPXConstants.DATE_TIME_SETTINGS_RECORD_NUMBER);
      m_buffer.append (m_delimiter);
      m_buffer.append(format(record.getDateOrder()));
      m_buffer.append (m_delimiter);
      m_buffer.append(format(record.getTimeFormat()));
      m_buffer.append (m_delimiter);
      m_buffer.append(format(getIntegerTimeInMinutes(record.getDefaultStartTime())));
      m_buffer.append (m_delimiter);
      m_buffer.append(format(new Character(record.getDateSeparator())));
      m_buffer.append (m_delimiter);
      m_buffer.append(format(new Character(record.getTimeSeparator())));
      m_buffer.append (m_delimiter);
      m_buffer.append(format(record.getAMText()));
      m_buffer.append (m_delimiter);
      m_buffer.append(format(record.getPMText()));
      m_buffer.append (m_delimiter);
      m_buffer.append(format(record.getDateFormat()));
      m_buffer.append (m_delimiter);
      m_buffer.append(format(record.getBarTextDateFormat()));
      stripTrailingDelimiters(m_buffer);
      m_buffer.append (MPXConstants.EOL);
      m_writer.write(m_buffer.toString());
      
      //
      // Write project calendars
      //
      for (ProjectCalendar cal : m_projectFile.getBaseCalendars())
      {
         writeCalendar(cal);
      }
      
      //
      // Project Header Record
      //
      m_buffer.setLength(0);
      m_buffer.append (MPXConstants.PROJECT_HEADER_RECORD_NUMBER);
      m_buffer.append (m_delimiter);
      m_buffer.append(format(record.getProjectTitle()));
      m_buffer.append (m_delimiter);
      m_buffer.append(format(record.getCompany()));
      m_buffer.append (m_delimiter);
      m_buffer.append(format(record.getManager()));
      m_buffer.append (m_delimiter);      
      m_buffer.append(format(record.getCalendarName()));
      m_buffer.append (m_delimiter);
      
      m_buffer.append(format(formatDateTime(record.getStartDate())));
      m_buffer.append (m_delimiter);
      m_buffer.append(format(formatDateTime(record.getFinishDate())));
      m_buffer.append (m_delimiter);
      m_buffer.append(format(record.getScheduleFrom()));
      m_buffer.append (m_delimiter);
      m_buffer.append(format(formatDateTime(record.getCurrentDate())));
      m_buffer.append (m_delimiter);
      m_buffer.append(format(record.getComments()));
      m_buffer.append (m_delimiter);
      m_buffer.append(format(formatCurrency(record.getCost())));
      m_buffer.append (m_delimiter);
      m_buffer.append(format(formatCurrency(record.getBaselineCost())));
      m_buffer.append (m_delimiter);
      m_buffer.append(format(formatCurrency(record.getActualCost())));
      m_buffer.append (m_delimiter);
      m_buffer.append(format(formatDuration(record.getWork())));
      m_buffer.append (m_delimiter);
      m_buffer.append(format(formatDuration(record.getBaselineWork())));
      m_buffer.append (m_delimiter);
      m_buffer.append(format(formatDuration(record.getActualWork())));
      m_buffer.append (m_delimiter);
      m_buffer.append(format(formatPercentage(record.getWork2())));
      m_buffer.append (m_delimiter);
      m_buffer.append(format(formatDuration(record.getDuration())));
      m_buffer.append (m_delimiter);
      m_buffer.append(format(formatDuration(record.getBaselineDuration())));
      m_buffer.append (m_delimiter);
      m_buffer.append(format(formatDuration(record.getActualDuration())));
      m_buffer.append (m_delimiter);
      m_buffer.append(format(formatPercentage(record.getPercentageComplete())));
      m_buffer.append (m_delimiter);
      m_buffer.append(format(formatDateTime(record.getBaselineStart())));
      m_buffer.append (m_delimiter);
      m_buffer.append(format(formatDateTime(record.getBaselineFinish())));
      m_buffer.append (m_delimiter);
      m_buffer.append(format(formatDateTime(record.getActualStart())));
      m_buffer.append (m_delimiter);
      m_buffer.append(format(formatDateTime(record.getActualFinish())));
      m_buffer.append (m_delimiter);
      m_buffer.append(format(formatDuration(record.getStartVariance())));
      m_buffer.append (m_delimiter);
      m_buffer.append(format(formatDuration(record.getFinishVariance())));
      m_buffer.append (m_delimiter);
      m_buffer.append(format(record.getSubject()));
      m_buffer.append (m_delimiter);
      m_buffer.append(format(record.getAuthor()));
      m_buffer.append (m_delimiter);
      m_buffer.append(format(record.getKeywords()));
      stripTrailingDelimiters(m_buffer);
      m_buffer.append (MPXConstants.EOL);

      m_writer.write(m_buffer.toString());
   }

   /**
    * Write a calendar.
    *
    * @param record calendar instance
    * @throws IOException
    */
   private void writeCalendar (ProjectCalendar record)
      throws IOException
   {
      m_buffer.setLength(0);

      if (record.getBaseCalendar() == null)
      {
         m_buffer.append (MPXConstants.BASE_CALENDAR_RECORD_NUMBER);
         m_buffer.append (m_delimiter);
         if (record.getName() != null)
         {
            m_buffer.append (record.getName());
         }
      }
      else
      {
         m_buffer.append (MPXConstants.RESOURCE_CALENDAR_RECORD_NUMBER);
         m_buffer.append (m_delimiter);
         m_buffer.append (record.getBaseCalendar().getName());
      }

      int[] days = record.getDays();
      for (int loop=0; loop < days.length; loop++)
      {
         m_buffer.append (m_delimiter);
         m_buffer.append (days[loop]);
      }

      m_buffer.append (MPXConstants.EOL);
      m_writer.write(m_buffer.toString());

      ProjectCalendarHours[] hours = record.getHours();
      for (int loop=0; loop < hours.length; loop++)
      {
         if (hours[loop] != null)
         {
            writeCalendarHours(record, hours[loop]);
         }
      }

      if (record.getCalendarExceptions().isEmpty() == false)
      {
         //
         // A quirk of MS Project is that these exceptions must be
         // in date order in the file, otherwise they are ignored
         //
         List<ProjectCalendarException> exceptions = new ArrayList<ProjectCalendarException>(record.getCalendarExceptions());
         Collections.sort(exceptions);
         
         for (ProjectCalendarException ex : exceptions)
         {
            writeCalendarException(record, ex);
         }
      }
   }

   /**
    * Write calendar hours.
    *
    * @param parentCalendar parent calendar instance
    * @param record calendar hours instance
    * @throws IOException
    */
   private void writeCalendarHours (ProjectCalendar parentCalendar, ProjectCalendarHours record)
      throws IOException
   {
      m_buffer.setLength(0);

      int recordNumber;

      if (parentCalendar.isBaseCalendar() == true)
      {
         recordNumber = MPXConstants.BASE_CALENDAR_HOURS_RECORD_NUMBER;
      }
      else
      {
         recordNumber = MPXConstants.RESOURCE_CALENDAR_HOURS_RECORD_NUMBER;
      }


      DateRange range1 = record.getDateRange(0);
      if (range1 == null)
      {
         range1 = DateRange.EMPTY_RANGE;
      }

      DateRange range2 = record.getDateRange(1);
      if (range2 == null)
      {
         range2 = DateRange.EMPTY_RANGE;
      }

      DateRange range3 = record.getDateRange(2);
      if (range3 == null)
      {
         range3 = DateRange.EMPTY_RANGE;
      }

      m_buffer.append (recordNumber);
      m_buffer.append (m_delimiter);
      m_buffer.append(format(record.getDay()));
      m_buffer.append (m_delimiter);
      m_buffer.append(format(formatTime(range1.getStartDate())));
      m_buffer.append (m_delimiter);
      m_buffer.append(format(formatTime(range1.getEndDate())));
      m_buffer.append (m_delimiter);
      m_buffer.append(format(formatTime(range2.getStartDate())));
      m_buffer.append (m_delimiter);
      m_buffer.append(format(formatTime(range2.getEndDate())));
      m_buffer.append (m_delimiter);
      m_buffer.append(format(formatTime(range3.getStartDate())));
      m_buffer.append (m_delimiter);
      m_buffer.append(format(formatTime(range3.getEndDate())));
      stripTrailingDelimiters(m_buffer);
      m_buffer.append (MPXConstants.EOL);

      m_writer.write(m_buffer.toString());
   }

   /**
    * Write a celandar exception.
    *
    * @param parentCalendar parent calendar instance
    * @param record calendar exception instance
    * @throws IOException
    */
   private void writeCalendarException(ProjectCalendar parentCalendar, ProjectCalendarException record)
      throws IOException
   {
      m_buffer.setLength(0);

      if (parentCalendar.isBaseCalendar() == true)
      {
         m_buffer.append(MPXConstants.BASE_CALENDAR_EXCEPTION_RECORD_NUMBER);
      }
      else
      {
         m_buffer.append(MPXConstants.RESOURCE_CALENDAR_EXCEPTION_RECORD_NUMBER);
      }
      m_buffer.append(m_delimiter);
      m_buffer.append(format(formatDate(record.getFromDate())));
      m_buffer.append(m_delimiter);
      m_buffer.append(format(formatDate(record.getToDate())));
      m_buffer.append(m_delimiter);
      m_buffer.append(record.getWorking()?"1":"0");
      m_buffer.append(m_delimiter);
      m_buffer.append(format(formatTime(record.getFromTime1())));
      m_buffer.append(m_delimiter);
      m_buffer.append(format(formatTime(record.getToTime1())));
      m_buffer.append(m_delimiter);
      m_buffer.append(format(formatTime(record.getFromTime2())));
      m_buffer.append(m_delimiter);
      m_buffer.append(format(formatTime(record.getToTime2())));
      m_buffer.append(m_delimiter);
      m_buffer.append(format(formatTime(record.getFromTime3())));
      m_buffer.append(m_delimiter);
      m_buffer.append(format(formatTime(record.getToTime3())));
      stripTrailingDelimiters(m_buffer);
      m_buffer.append (MPXConstants.EOL);

      m_writer.write(m_buffer.toString());
   }

   /**
    * Write a resource.
    *
    * @param record resource instance
    * @throws IOException
    */
   private void writeResource(Resource record)
      throws IOException
   {
      m_buffer.setLength(0);

      //
      // Write the resource record
      //
      int[] fields = m_resourceModel.getModel();

      m_buffer.append(MPXConstants.RESOURCE_RECORD_NUMBER);
      for (int loop=0; loop < fields.length; loop++)
      {
         int mpxFieldType = fields[loop];
         if (mpxFieldType == -1)
         {
            break;
         }

         ResourceField resourceField = MPXResourceField.getMpxjField(mpxFieldType);
         Object value = record.getCachedValue(resourceField);
         value = formatType(resourceField.getDataType(), value);

         m_buffer.append (m_delimiter);
         m_buffer.append (format (value));
      }

      stripTrailingDelimiters (m_buffer);
      m_buffer.append (MPXConstants.EOL);
      m_writer.write(m_buffer.toString());

      //
      // Write the resource notes
      //
      String notes = record.getNotes();
      if (notes != null && notes.length() != 0)
      {
         writeNotes(MPXConstants.RESOURCE_NOTES_RECORD_NUMBER, notes);
      }

      //
      // Write the resource calendar
      //
      if (record.getResourceCalendar() != null)
      {
         writeCalendar(record.getResourceCalendar());
      }

      m_projectFile.fireResourceWrittenEvent(record);
   }

   /**
    * Write notes.
    *
    * @param recordNumber record number
    * @param text note text
    * @throws IOException
    */
   private void writeNotes(int recordNumber, String text)
      throws IOException
   {
      m_buffer.setLength(0);

      m_buffer.append (recordNumber);
      m_buffer.append (m_delimiter);

      if (text != null)
      {
         String note = stripLineBreaks(text, MPXConstants.EOL_PLACEHOLDER_STRING);
         boolean quote = (note.indexOf(m_delimiter) != -1 || note.indexOf('"') != -1);
         int length = note.length();
         char c;

         if (quote == true)
         {
            m_buffer.append('"');
         }

         for (int loop=0; loop < length; loop++)
         {
            c = note.charAt(loop);

            switch (c)
            {
               case '"':
               {
                  m_buffer.append ("\"\"");
                  break;
               }

               default:
               {
                  m_buffer.append (c);
                  break;
               }
            }
         }

         if (quote == true)
         {
            m_buffer.append('"');
         }
      }

      m_buffer.append (MPXConstants.EOL);

      m_writer.write(m_buffer.toString());
   }

   /**
    * Write a task.
    *
    * @param record task instance
    * @throws IOException
    */
   private void writeTask (Task record)
      throws IOException
   {
      m_buffer.setLength(0);

      //
      // Write the task
      //
      int[] fields = m_taskModel.getModel();
      int field;

      m_buffer.append(MPXConstants.TASK_RECORD_NUMBER);
      for (int loop=0; loop < fields.length; loop++)
      {
         field = fields[loop];
         if (field == -1)
         {
            break;
         }

         TaskField taskField = MPXTaskField.getMpxjField(field);
         Object value = record.getCachedValue(taskField);
         value = formatType(taskField.getDataType(), value);

         m_buffer.append (m_delimiter);
         m_buffer.append (format (value));
      }

      stripTrailingDelimiters (m_buffer);
      m_buffer.append (MPXConstants.EOL);
      m_writer.write(m_buffer.toString());

      //
      // Write the task notes
      //
      String notes = record.getNotes();
      if (notes != null && notes.length() != 0)
      {
         writeNotes(MPXConstants.TASK_NOTES_RECORD_NUMBER, notes);
      }

      //
      // Write the recurring task
      //
      if (record.getRecurringTask() != null)
      {
         writeRecurringTask(record.getRecurringTask());
      }

      //
      // Write any resource assignments
      //
      if (record.getResourceAssignments().isEmpty() == false)
      {
         for (ResourceAssignment assignment : record.getResourceAssignments())
         {
            writeResourceAssignment(assignment);
         }
      }

      m_projectFile.fireTaskWrittenEvent(record);
   }

   /**
    * Write a recurring task.
    * 
    * @param record recurring task instance
    * @throws IOException
    */
   private void writeRecurringTask (RecurringTask record)
      throws IOException
   {
      m_buffer.setLength(0);

      m_buffer.append(MPXConstants.RECURRING_TASK_RECORD_NUMBER);
      m_buffer.append(m_delimiter);
      m_buffer.append(format(record.getTaskUniqueID()));
      
      if (record.getRecurrenceType() != null)
      {
         m_buffer.append(m_delimiter);
         m_buffer.append(format(formatDateTime(record.getStartDate())));
         m_buffer.append(m_delimiter);
         m_buffer.append(format(formatDateTime(record.getFinishDate())));
         m_buffer.append(m_delimiter);
         m_buffer.append(format(RecurrenceUtility.getDurationValue(m_projectFile.getProjectHeader(), record.getDuration())));
         m_buffer.append(m_delimiter);
         m_buffer.append(format(RecurrenceUtility.getDurationUnits(record)));
         m_buffer.append(m_delimiter);
         m_buffer.append(format(record.getOccurrences()));
         m_buffer.append(m_delimiter);
         m_buffer.append(format(RecurrenceUtility.getRecurrenceValue(record.getRecurrenceType())));
         m_buffer.append(m_delimiter);
         m_buffer.append(format(record.getNotSureIndex()));
         m_buffer.append(m_delimiter);
         m_buffer.append(format(record.getLengthRadioIndex()));
         m_buffer.append(m_delimiter);
         m_buffer.append(format(RecurrenceUtility.getDailyWorkday(record.getDailyWorkday())));
         m_buffer.append(m_delimiter);
         m_buffer.append(format(RecurrenceUtility.getDays(record.getWeeklyDays())));
         m_buffer.append(m_delimiter);
         m_buffer.append(format(record.getMonthlyBoxRadioIndex()));
         m_buffer.append(m_delimiter);
         m_buffer.append(format(record.getYearlyBoxRadioIndex()));
         m_buffer.append(m_delimiter);
         m_buffer.append(format(record.getDailyFrequency()));
         m_buffer.append(m_delimiter);
         m_buffer.append(format(record.getWeeklyFrequency()));
         m_buffer.append(m_delimiter);
         m_buffer.append(format(record.getMonthlyBoxFirstLastComboIndex()));
         m_buffer.append(m_delimiter);
         m_buffer.append(format(record.getMonthlyBoxDayComboIndex()));
         m_buffer.append(m_delimiter);
         m_buffer.append(format(record.getMonthlyBoxBottomRadioFrequencyComboIndex()));
         m_buffer.append(m_delimiter);
         m_buffer.append(format(record.getMonthlyBoxDayIndex()));
         m_buffer.append(m_delimiter);
         m_buffer.append(format(record.getMonthlyBoxTopRadioFrequencyComboIndex()));
         m_buffer.append(m_delimiter);
         m_buffer.append(format(record.getYearlyBoxFirstLastComboIndex()));
         m_buffer.append(m_delimiter);
         m_buffer.append(format(record.getYearlyBoxDayComboIndex()));
         m_buffer.append(m_delimiter);
         m_buffer.append(format(record.getYearlyBoxMonthComboIndex()));
         m_buffer.append(m_delimiter);
         m_buffer.append(format(formatDateTime(record.getYearlyBoxDate())));
   
         stripTrailingDelimiters(m_buffer);
      }
      m_buffer.append (MPXConstants.EOL);

      m_writer.write(m_buffer.toString());
   }

   /**
    * Write resource assignment.
    *
    * @param record resource assignment instance
    * @throws IOException
    */
   private void writeResourceAssignment(ResourceAssignment record)
      throws IOException
   {
      m_buffer.setLength(0);

      m_buffer.append(MPXConstants.RESOURCE_ASSIGNMENT_RECORD_NUMBER);
      m_buffer.append(m_delimiter);
      m_buffer.append(format(record.getResource().getID()));
      m_buffer.append(m_delimiter);
      m_buffer.append(format(formatUnits(record.getUnits())));
      m_buffer.append(m_delimiter);
      m_buffer.append(format(formatDuration(record.getWork())));
      m_buffer.append(m_delimiter);
      m_buffer.append(format(formatDuration(record.getPlannedWork())));
      m_buffer.append(m_delimiter);
      m_buffer.append(format(formatDuration(record.getActualWork())));
      m_buffer.append(m_delimiter);
      m_buffer.append(format(formatDuration(record.getOvertimeWork())));
      m_buffer.append(m_delimiter);
      m_buffer.append(format(formatCurrency(record.getCost())));
      m_buffer.append(m_delimiter);
      m_buffer.append(format(formatCurrency(record.getPlannedCost())));
      m_buffer.append(m_delimiter);
      m_buffer.append(format(formatCurrency(record.getActualCost())));
      m_buffer.append(m_delimiter);
      m_buffer.append(format(formatDateTime(record.getStart())));
      m_buffer.append(m_delimiter);
      m_buffer.append(format(formatDateTime(record.getFinish())));
      m_buffer.append(m_delimiter);
      m_buffer.append(format(formatDuration(record.getDelay())));
      m_buffer.append(m_delimiter);
      m_buffer.append(format(record.getResourceUniqueID()));
      stripTrailingDelimiters(m_buffer);
      m_buffer.append (MPXConstants.EOL);
      m_writer.write(m_buffer.toString());

      if (record.getWorkgroupAssignment() != null)
      {
         writeResourceAssignmentWorkgroupFields(record.getWorkgroupAssignment());
      }
   }

   /**
    * Write resource assignment workgroup.
    *
    * @param record resource assignment workgroup instance
    * @throws IOException
    */
   private void writeResourceAssignmentWorkgroupFields(ResourceAssignmentWorkgroupFields record)
      throws IOException
   {
      m_buffer.setLength(0);

      m_buffer.append(MPXConstants.RESOURCE_ASSIGNMENT_WORKGROUP_FIELDS_RECORD_NUMBER);
      m_buffer.append(m_delimiter);
      m_buffer.append(format(record.getMessageUniqueID()));
      m_buffer.append(m_delimiter);
      m_buffer.append(record.getConfirmed()?"1":"0");
      m_buffer.append(m_delimiter);
      m_buffer.append(record.getResponsePending()?"1":"0");
      m_buffer.append(m_delimiter);
      m_buffer.append(format(formatDateTime(record.getUpdateStart())));
      m_buffer.append(m_delimiter);
      m_buffer.append(format(formatDateTime(record.getUpdateFinish())));
      m_buffer.append(m_delimiter);
      m_buffer.append(format(record.getScheduleID()));

      stripTrailingDelimiters(m_buffer);
      m_buffer.append (MPXConstants.EOL);

      m_writer.write(m_buffer.toString());
   }

   /**
    * Recursively write tasks.
    *
    * @param tasks list of tasks
    * @throws IOException
    */
   private void writeTasks (List<Task> tasks)
      throws IOException
   {
      for (Task task : tasks)
      {         
         writeTask(task);
         writeTasks(task.getChildTasks());
      }
   }

   /**
    * This internal method is used to convert from a Date instance to an
    * integer representing the number of minutes past midnight.
    *
    * @param date date instance
    * @return minutes past midnight as an integer
    */
   private Integer getIntegerTimeInMinutes (Date date)
   {
      Integer result = null;
      if (date != null)
      {
         Calendar cal = Calendar.getInstance();
         cal.setTime(date);
         int time = cal.get(Calendar.HOUR_OF_DAY) * 60;
         time += cal.get(Calendar.MINUTE);
         result = new Integer (time);
      }
      return (result);
   }

   /**
    * This method is called when double quotes are found as part of
    * a value. The quotes are escaped by adding a second quote character
    * and the entire value is quoted.
    *
    * @param value text containing quote characters
    * @return escaped and quoted text
    */
   private String escapeQuotes (String value)
   {
      StringBuffer sb = new StringBuffer();
      int length = value.length();
      char c;

      sb.append('"');
      for (int index = 0; index < length; index++)
      {
         c = value.charAt(index);
         sb.append(c);

         if (c == '"')
         {
            sb.append('"');
         }
      }
      sb.append('"');

      return (sb.toString());
   }

   /**
    * This method removes line breaks from a piece of text, and replaces
    * them with the supplied text.
    *
    * @param text source text
    * @param replacement line break replacement text
    * @return text with line breaks removed.
    */
   private String stripLineBreaks (String text, String replacement)
   {
      if (text.indexOf('\r') != -1 || text.indexOf('\n') != -1)
      {
         StringBuffer sb = new StringBuffer (text);

         int index;

         while ((index = sb.indexOf("\r\n")) != -1)
         {
            sb.replace(index, index+2, replacement);
         }

         while ((index = sb.indexOf("\n\r")) != -1)
         {
            sb.replace(index, index+2, replacement);
         }

         while ((index = sb.indexOf("\r")) != -1)
         {
            sb.replace(index, index+1, replacement);
         }

         while ((index = sb.indexOf("\n")) != -1)
         {
            sb.replace(index, index+1, replacement);
         }

         text = sb.toString();
      }

      return (text);
   }

   /**
    * This method returns the string representation of an object. In most
    * cases this will simply involve calling the normal toString method
    * on the object, but a couple of exceptions are handled here.
    *
    * @param o the object to formatted
    * @return formatted string representing input Object
    */
   private String format (Object o)
   {
      String result;

      if (o == null)
      {
         result = "";
      }
      else
      {
         if (o instanceof Boolean == true)
         {
            result = LocaleData.getString(m_locale, (((Boolean)o).booleanValue() == true?LocaleData.YES:LocaleData.NO));
         }
         else
         {
            if (o instanceof Float == true || o instanceof Double == true)
            {
               result = (m_formats.getDecimalFormat().format(((Number)o).doubleValue()));
            }
            else
            {
               result = o.toString();
            }
         }

         //
         // At this point there should be no line break characters in
         // the file. If we find any, replace them with spaces
         //
         result = stripLineBreaks(result, MPXConstants.EOL_PLACEHOLDER_STRING);

         //
         // Finally we check to ensure that there are no embedded
         // quotes or separator characters in the value. If there are, then
         // we quote the value and escape any existing quote characters.
         //
         if (result.indexOf('"') != -1)
         {
            result = escapeQuotes(result);
         }
         else
         {
            if (result.indexOf(m_delimiter) != -1)
            {
               result = '"' + result + '"';
            }
         }
      }

      return (result);
   }


   /**
    * This method removes trailing delimiter characters.
    *
    * @param buffer input sring buffer
    */
   private void stripTrailingDelimiters (StringBuffer buffer)
   {
      int index = buffer.length() - 1;

      while (index > 0 && buffer.charAt(index) == m_delimiter)
      {
         --index;
      }

      buffer.setLength (index+1);
   }

   /**
    * This method is called to format a time value.
    *
    * @param value time value
    * @return formatted time value
    */
   private String formatTime (Date value)
   {
      return (value==null?null:m_formats.getTimeFormat().format(value));
   }

   /**
    * This method is called to format a currency value.
    *
    * @param value numeric value
    * @return currency value
    */
   private String formatCurrency (Number value)
   {
      return (value==null?null:m_formats.getCurrencyFormat().format(value));
   }

   /**
    * This method is called to format a units value.
    *
    * @param value numeric value
    * @return currency value
    */
   private String formatUnits (Number value)
   {
      return (value==null?null:m_formats.getUnitsDecimalFormat().format(value.doubleValue()/100));
   }

   /**
    * This method is called to format a date.
    *
    * @param value date value
    * @return formatted date value
    */
   private String formatDateTime (Date value)
   {
      return (value==null?null:m_formats.getDateTimeFormat().format(value));
   }

   /**
    * This method is called to format a date.
    *
    * @param value date value
    * @return formatted date value
    */
   private String formatDate (Date value)
   {
      return (value==null?null:m_formats.getDateFormat().format(value));
   }

   /**
    * This method is called to format a percentage value.
    *
    * @param value numeric value
    * @return percentage value
    */
   private String formatPercentage (Number value)
   {
      return (value==null?null:m_formats.getPercentageDecimalFormat().format(value) + "%");
   }

   /**
    * This method is called to format an accrue type value.
    *
    * @param type accrue type
    * @return formatted accrue type
    */
   private String formatAccrueType (AccrueType type)
   {      
      return (type==null?null:LocaleData.getStringArray(m_locale, LocaleData.ACCRUE_TYPES)[type.getValue()-1]);
   }

   /**
    * This method is called to format a constraint type.
    *
    * @param type constraint type
    * @return formatted constraint type
    */
   private String formatConstraintType (ConstraintType type)
   {
      return (type==null?null:LocaleData.getStringArray(m_locale, LocaleData.CONSTRAINT_TYPES)[type.getValue()]);
   }

   /**
    * This method is called to format a duration.
    *
    * @param value duration value
    * @return formatted duration value
    */
   private String formatDuration (Duration value)
   {
      return (value==null?null:m_formats.getDurationDecimalFormat().format(value.getDuration()) + formatTimeUnit(value.getUnits()));
   }

   /**
    * This method is called to format a rate.
    *
    * @param value rate value
    * @return formatted rate
    */
   private String formatRate (Rate value)
   {
      String result = null;
      if (value != null)
      {
         StringBuffer buffer = new StringBuffer (m_formats.getCurrencyFormat().format(value.getAmount()));
         buffer.append ("/");
         buffer.append (formatTimeUnit(value.getUnits()));
         result = buffer.toString();
      }
      return (result);
   }

   /**
    * This method is called to format a priority.
    *
    * @param value priority instance
    * @return formatted priority value
    */
   private String formatPriority (Priority value)
   {
      String result = null;
      
      if (value != null)
      {
         String[] priorityTypes = LocaleData.getStringArray(m_locale, LocaleData.PRIORITY_TYPES);
         int priority = value.getValue();
         if (priority < Priority.LOWEST)
         {
            priority = Priority.LOWEST;
         }
         else
         {
            if (priority > Priority.DO_NOT_LEVEL)
            {
               priority = Priority.DO_NOT_LEVEL;
            }
         }
   
         priority /= 100;
         
         result = priorityTypes[priority-1];
      }
      
      return (result);
   }

   /**
    * This method is called to format a task type.
    * 
    * @param value task type value
    * @return formatted task type
    */
   private String formatTaskType (TaskType value)
   {
      return (LocaleData.getString(m_locale, (value == TaskType.FIXED_DURATION?LocaleData.YES:LocaleData.NO)));         
   }
   
   /**
    * This method is called to format a relation list.
    *
    * @param value relation list instance
    * @return formatted relation list
    */
   private String formatRelationList (List<Relation> value)
   {
      String result = null;

      if (value != null)
      {
         StringBuffer sb = new StringBuffer();
         for (Relation relation: value)
         {
            if (sb.length() != 0)
            {
               sb.append(m_delimiter);
            }

            sb.append(formatRelation(relation));
         }

         result = sb.toString();
      }

      return (result);
   }

   /**
    * This method is called to format a relation.
    *
    * @param relation relation instance
    * @return formatted relation instance
    */
   private String formatRelation (Relation relation)
   {
      String result = null;
      
      if (relation != null)
      {
         StringBuffer sb = new StringBuffer(relation.getTask().getID().toString());
   
         Duration duration = relation.getDuration();
         RelationType type = relation.getType();
         double durationValue = duration.getDuration();
   
         if ((durationValue != 0) || (type != RelationType.FINISH_START))
         {
            String[] typeNames = LocaleData.getStringArray(m_locale, LocaleData.RELATION_TYPES);
            sb.append (typeNames[type.getValue()]);
         }
   
         if (durationValue != 0)
         {
            if (durationValue > 0)
            {
               sb.append('+');
            }
   
            sb.append(formatDuration(duration));
         }
         
         result = sb.toString();
      }
      
      return (result);
   }

   /**
    * This method formats a time unit.
    *
    * @param timeUnit time unit instance
    * @return formatted time unit instance
    */
   private String formatTimeUnit (TimeUnit timeUnit)
   {
      int units = timeUnit.getValue();
      String result;
      String[] unitNames = LocaleData.getStringArray(m_locale, LocaleData.TIME_UNITS_ARRAY);

      if (units < 0 || units >= unitNames.length)
      {
         result = "";
      }
      else
      {
         result = unitNames[units];
      }

      return (result);
   }

   /**
    * This method formats a decimal value.
    *
    * @param value value
    * @return formatted value
    */
   private String formatDecimal (double value)
   {
      return (m_formats.getDecimalFormat().format(value));
   }
   
   /**
    * Converts a value to the appropriate type.
    *
    * @param type target type
    * @param value input value
    * @return output value
    */
   @SuppressWarnings("unchecked")
   private Object formatType (DataType type, Object value)
   {
      switch (type)
      {
         case DATE:
         {
            value = formatDateTime((Date)value);
            break;
         }

         case CURRENCY:
         {
            value = formatCurrency((Number)value);
            break;
         }

         case UNITS:
         {
            value = formatUnits((Number)value);
            break;
         }

         case PERCENTAGE:
         {
            value = formatPercentage((Number)value);
            break;
         }

         case ACCRUE:
         {
            value = formatAccrueType((AccrueType)value);
            break;
         }

         case CONSTRAINT:
         {
            value = formatConstraintType((ConstraintType)value);
            break;
         }

         case DURATION:
         {
            value = formatDuration((Duration)value);
            break;
         }

         case RATE:
         {
            value = formatRate((Rate)value);
            break;
         }

         case PRIORITY:
         {
            value = formatPriority((Priority)value);
            break;
         }

         case RELATION_LIST:
         {
            value = formatRelationList((List<Relation>)value);
            break;
         }

         case TASK_TYPE:
         {
            value = formatTaskType ((TaskType)value);
            break;
         }
         
         default:
         {
            break;
         }
      }

      return (value);
   }

   /**
    * This method returns the locale used by this MPX file.
    *
    * @return current locale
    */
   public Locale getLocale ()
   {
      return (m_locale);
   }

   /**
    * This method sets the locale to be used by this MPX file.
    *
    * @param locale locale to be used
    */
   public void setLocale (Locale locale)
   {
      m_locale = locale;
   }
   
      
   private ProjectFile m_projectFile;
   private OutputStreamWriter m_writer;
   private ResourceModel m_resourceModel;
   private TaskModel m_taskModel;
   private char m_delimiter;
   private Locale m_locale = Locale.ENGLISH;
   private StringBuffer m_buffer;
   private MPXJFormats m_formats;
}
