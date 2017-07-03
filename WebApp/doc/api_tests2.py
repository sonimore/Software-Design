'''
  api_tests.py
   Ethan Somes
   Nyla Worker
   Sonia Moreno
   April 24, 2017
'''

import unittest

class apiTester(unittest.TestCase):
    
    def setUp(self):
       pass

    def tearDown(self):
        pass

    """
    ASTEROID TABLE TESTS
    """
    def testListAllAsteroidsEmpty(self):
        #Get a list of all asteroid designations (names) in the database, and are none
        self.assertEqual(len(getDesignationList()), 0)

    def testListAllAsteroidContains(self):
        #Get a list of all asteroid designations (names) in the database
        asterNames = getDesignationList()
        self.assertTrue(self.assertIn('1979 XB', asterNames) and
        self.assertIn('101955 Bennu (1999 RQ36)', asterNames) and
        self.assertIn('(2010 XB73)', asterNames) and
        self.assertIn('(2005 TM173)', asterNames) and
        self.assertIn('(2015 ME131)', asterNames) and
        self.assertIn('(2007 FT3)', asterNames) and
        self.assertIn('(2008 UB7)', asterNames))

    def testGetData(self):
        #Get a dictionary of data for a spacific asteroid given a designation
        dictionary = getInfo("(2007 FT3)")
        self.assertTrue(dictionary.get('impact_probability').assertIn(0.000000092) and
            dictionary.get('palermo_score').assertIn(-3.17))


    def testDateEmpty(self):
        #Get a list of asteroid designations (names) with the given year. 
        self.assertEqual(len(get_dates(1990)), 0)

    def testDate2020(self):
        #Get a list of asteroid designations (names) with the given year. 
        dates = get_dates('2020')
        self.assertTrue(self.assertIn('(2001 CA21)', dates) and
        self.assertIn('(2016 NL56)', dates)and
        self.assertIn('(2016 WN55)', dates))


    def testDate2047(self):
        #Get a list of asteroid designations (names) with the given year. 
        dates2 = get_dates('2047')
        self.assertTrue(self.assertIn('(2008 UV99)', dates)and
        self.assertIn('(2016 NL56)', dates)and
        self.assertIn('(2016 WN55)', dates))

    def testDate2059(self):
        #Get a list of asteroid designations (names) with the given year. 
        dates2 = get_dates('2059')
        self.assertTrue(self.assertIn('(2000 WJ107)', dates) and # the only asteroid with 2059 predicted hit year
        self.assertNotIn('(2008 UV99)', dates) and # assuring that surrounding dates aren't in list
        self.assertNotIn('(2015 ME131)', dates))

    
    def testScoresEmpty(self): 
        self.assertEqual(len(getScoreList(100)), 0)

    def testScores32(self):
        """Checks that the getScoreList method returns a list of items with 
        score within .1 points (Palermo scale) of score passed in
        """
        scores = getScoreList(-3.2)
        self.assertIn('(1979 XB)', scores)
        self.assertIn('(2007 FT3)', scores)
        self.assertIn('101955 Bennu (1999 RQ36)', scores)

        # checking edge case (score of -3.35)
        self.assertNotIn('101955 Bennu (1999 RQ36)', scores)

    def testScores418(self):
        """Checks that the getScoreList method returns a list of items with 
    score within .1 points (Palermo scale) of score passed in
    """
        scores2 = getScoreList(-4.18)
        self.assertIn('(2017 FN128)', scores)
        self.assertIn('101955 Bennu (1999 RQ36)', scores2)
        self.assertIn('(2017 GM7)', scores)
        self.assertIn('99942 Apophis (2004 MN4)', scores2)
        self.assertIn('(2010 AU118)', scores) # lower bound (-4.28)
        self.assertIn('99942 Apophis (2004 MN4)', scores2) # upper bound (-4.09)

    

    def testProbabilities(self):
        #Get a dictionary of probabilities (key is date) given the asteriod name 
        dictionary = getProbabilities('(1979 XB')
        self.assertEqual(dictionary.get(2056), 0.00000018)


    """
    NEAR EARTH OBJECTS TESTS
    """

    def testListAllAsteroidsEmpty(self):
        #Get a list of all asteroid designations (names) in the database, and are none
        self.assertEqual(len(getNearEarthObjects()), 0)

    def testAllNearEarthObjects(self):
        #Get a list of all near earth objects in the database
        earthObjects = getNearEarthObjects()

        self.assertIn('(2007 AG)', earthObjects)
        self.assertIn('(2009 BW2)', earthObjects)
        self.assertIn('419472 (2010 DW1)', earthObjects)
        self.assertIn('(2007 ED125)', earthObjects)
        self.assertIn('418135 (2008 AG33)', earthObjects)
        self.assertIn('(2005 UH5)', earthObjects)
        self.assertIn('(2007 SV1)', earthObjects)
        self.assertIn('(2017 FK)', earthObjects)
        self.assertIn('(2006 HC)', earthObjects)
        self.assertIn('453563 (2010 BB)', earthObjects)
        self.assertIn('(2004 TP1)', earthObjects)
        self.assertIn('2010 TK19)', earthObjects)

        
    def testDateNone(self):
        #Get a list of asteroid designations (names) with the given year. 
        self.assertEqual(len(get_dates(1000)), 0)

    def testDate1900(self):
        '''Creates an instance of get_dates from the year 1900, which returns a

        list of objects that approached/will approach the earth in that year

        '''
        dates = get_dates(1900)

        self.assertIn('348306 (2005 AY28)', dates)
        self.assertIn('4660 Nereus (1982 DB)', dates)
        self.assertIn('378160 (2006 WX1)', dates)
        self.assertIn('(2007 ED125)', dates)
        self.assertIn('461852 (2006 GY2)', dates)
        self.assertIn('(2011 WS74)', dates)
        self.assertNotIn('199003 (2005 WJ56)', dates) # boundary case (Jan, 1901)

        

    def testDate1982(self):
        '''Creates an instance of get_dates from the year 1983, which returns a

        list of objects that approached/will approach the earth in that year

        '''

        dates = get_dates(1982)

        self.assertIn('3757 Anagolay (1982 XB)', dates)
        self.assertIn('450237 (2002 XY38)', dates)
        self.assertIn('(2013 ST)', dates)
        self.assertIn('(2010 WR7)', dates)
        self.assertIn('(2016 FY2)', dates)
        self.assertIn('(2010 JR34)', dates)
        self.assertNotIn('(2016 NG15)', dates) # boundary case (Dec, 1981)
        self.assertNotIn('471240 (2011 BT15)', dates) # boundary case (Jan, 1983)

    def testGetDataNEO(self):    
    #Get a dictionary of data for a specific NEO given a designation
    dictionary = getInfoNEO("(2003 FK1)")
        self.assertTrue(dictionary.get('Hmag').assertIn(23) and
            dictionary.get('vrelaa').assertIn(14.78))

    def testDiameters(self):
        #Get a dictionary of all close earth approaches (names) with it's diameters
        dictionary = getDiameters()
        self.assertTrue(dictionary.get("(2009 HU44)") == "80 m -  180 m")

if __name__ == '__main__':
    unittest.main()
