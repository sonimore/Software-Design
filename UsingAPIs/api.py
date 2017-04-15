'''
    api_test.py
    Jeff Ondich, 11 April 2016
    Ethan Somes, 13 April, 2017
    Sonia Moreno, 14 April, 2017
    Revised from Jeff's example for CS 257 Software Design. How to retrieve results
    from an HTTP-based API, parse the results (JSON in this case),
    and manage the potential errors.
    
    Api from newsapi.org
    apiKey = 82cf4993bd7b404abae9673a74e61d01
'''

import sys
import argparse
import json
import urllib.request
import re


def get_headlines(source, sorting):
   '''
   Returns a list of headlines and authors of the articles 
   sorted in the way inputted. They are
   organized in a dictionary in the form:
   
      {'title':title, 'author':author}
   The source parameter must one of the 70 souces formatted as seen on
   https://newsapi.org/sources
   The sorting can be top, latest, or popular. 
   Raises exceptions on network connection errors and on data
   format errors. Maybe?
   '''
   base_url = 'https://newsapi.org/v1/articles?source='
   apiKey= '&apiKey=82cf4993bd7b404abae9673a74e61d01'
   base_url = base_url + source + '&sortby=' + sorting + apiKey

   data_from_server = urllib.request.urlopen(base_url).read()
   string_from_server = data_from_server.decode('utf-8')
   loaded = json.loads(string_from_server)
   result_list = []
   article_list = loaded['articles']
   for article_dictionary in article_list:
      title = article_dictionary['title']
      author= article_dictionary['author']
      if type(author) != type(''):
         raise Exception('author has wrong type: "{0}"'.format(author))
      if type(title) != type(''):
         raise Exception('title has wrong type: "{0}"'.format(title))
      result_list.append({'title':title, 'author':author})
   return result_list


def get_Description(source, title):
   '''
   Returns a dictionary containing the title of the article in question 
   and the descrtiption of it.
   
      
   The source parameter must one of the 7 souces formatted as seen on
   https://newsapi.org/sources
   Raises exceptions on network connection errors and on data
   format errors.
   '''
   base_url = 'https://newsapi.org/v1/articles?source='
   apiKey = '&apiKey=82cf4993bd7b404abae9673a74e61d01'
   url = base_url + source + apiKey

   data_from_server = urllib.request.urlopen(url).read()
   string_from_server = data_from_server.decode('utf-8')
   loaded = json.loads(string_from_server)
   article_list = loaded['articles']

   result_dict = {}
   description = ""
   for article_dictionary in article_list:
      dictEntry = re.sub("[^a-z0-9]+","", article_dictionary['title'], flags=re.IGNORECASE)
      titleEntry = re.sub("[^a-z0-9]+","", title, flags=re.IGNORECASE)
      if (dictEntry == titleEntry):
         description = article_dictionary['description']
      if type(description) != type(''):
         raise Exception('text has wrong type: "{0}"'.format(text))
   result_dict['title'] = title
   if description == "":
      description = "This article could not be found."
   result_dict['description'] = description
       
   return result_dict


  

def userMain():
#    An option for users who do not want to parse from the command line. Gives more instruction
#    Also used for testing.

   print("This program allows you to look at news headlines from many sources!")
   print("The 70 news sources available and thier codes are listed here: https://newsapi.org/sources")
   print("You can either search for the description of a particular article from a source,")
   print("or you can search for a list of headlines from a source.")
   userInput = input("Would you like a description or a list of headlines? Enter description or list.  ")
   source = input("Enter a news source code:  ")
   if userInput == "description":
      title = input("What is the title of the article you want to look at?")
      description_Dict = get_Description(source, title)
      print("Title: " + title)
      print(description_Dict['description'])
   
   elif userInput == "list":
      sorting = input("How would you like the list to be sorted? Enter top, latest, or popular.  ")
      headlines_list = get_headlines(source, sorting)
      for dictionary in headlines_list:
         print(dictionary['title'] + ": " + dictionary['author'])
 
  
def main(args):
  # Uses inputs from command line, else runs main(). 
   if args.action == "description":
      description_Dict = get_Description(args.source, args.title)
      print("Title: " + args.title)
      print(description_Dict['description'])
   
   elif args.action == "list":
      headlines_list = get_headlines(args.source, args.sorting)
      for dictionary in headlines_list:
         print(dictionary['title'] + ": " + dictionary['author'])


if __name__ == '__main__':
   '''
   When I use argparse to parse my command line, I usually
   put the argparse setup here in the global code, and then
   call a function called main to do the actual work of
   the program.
   '''

   parser = argparse.ArgumentParser(description='Get information of recent headlines using NewsAPI')

   parser.add_argument('action',
                      metavar='action',
                      help='action to perform ("description" or "list")',
                      choices=['description', 'list'])

   parser.add_argument('source',
                      metavar='source',
                      help='The source parameter must one of the 70 souces formatted as seen on \
                         https://newsapi.org/sources')

   parser.add_argument('sorting',
                      metavar = 'sorting', 
                      help='if you chose list, enter either popular, top or latest. Else, enter none.',
                      choices = ['top', 'latest', 'popular', 'none'])

   # it's difficult to parse a title, because it is many words, so it must be all one word, or each word is separated 
   # by a dash or other punctuation
   parser.add_argument('title', 
                      metavar = 'title',
                      help='if you chose description, enter the title of the article you are looking for \
                        more information about, all as one word. If you chose list, enter none')

   args = parser.parse_args()
   main(args)