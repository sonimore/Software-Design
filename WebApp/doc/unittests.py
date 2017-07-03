'''
   api_tests.py
   Ethan Somes
   Nyla Worker
   Sonia Moreno
   April 24, 2017
'''

import unittest
from  neo_api import *


class apiTester(unittest.TestCase):
    def setUp(self):
        pass

    def tearDown(self):
        pass

    """
    ASTEROID TABLE TESTS
    """

    def testListAllAsteroidsEmpty(self):
        '''
        Get a list of all asteroid designations (names) in the database
        Make sure there are not none (edge case)
        '''
        self.assertFalse(len(get_asteroids())==0)

    def testListAllAsteroidContains(self):
        '''
        Get a list of all asteroid designations (names) in the database
        Make sure there are 
        '''
        asterNames = json.loads(get_asteroids())
        self.assertEqual('(1979 XB)', asterNames[1]['AsteroidDesignation'])
        self.assertEqual('(2005 NX55)', asterNames[68]['AsteroidDesignation'])
        self.assertEqual('(2007 FT3)', asterNames[120]['AsteroidDesignation'])

    def testGetOneAsteroid(self):
        # Get a dictionary of data for a spacific asteroid given a designation
        dictionary = json.loads(get_asteroid_by_designation("(2007 FT3)"))
        self.assertEqual(dictionary['ImpactProbability'],0.000000092)
        self.assertTrue(dictionary['palermoScale']==-3.17)

    def testDateEmpty(self):
        # Get a list of asteroid designations (names) with the given year.
        self.assertTrue(len(json.loads(get_asteroid_dates(1990)))== 0)

    def testDate2020(self):
        # Get a list of asteroid designations (names) with the given year.
        dates = json.loads(get_asteroid_dates(2020))
        self.assertEqual('(2001 CA21)', dates[1]['AsteroidDesignation'])
        self.assertEqual('(2016 NL56)', dates[9]['AsteroidDesignation'])
        self.assertEqual('(2016 WN55)', dates[11]['AsteroidDesignation'])

    def testDate2059(self):
        # Get a list of asteroid designations (names) with the given year
        dates = json.loads(get_asteroid_dates(2880))
        self.assertTrue(len(dates)==2)

    def testScoresEmpty(self):
        # Checks that there are no asteroids with score 100
        self.assertEqual(len(json.loads(get_asteroids_by_score(100))), 0)

    def testScores32(self):
        """
        Checks that the getScoreList method returns a list of items with
        score within .1 points (Palermo scale) of score passed in
        """
        scores = json.loads(get_asteroids_by_score(3.2))
        self.assertEqual('101955 Bennu (1999 RQ36)', scores[1]['AsteroidDesignation'])
    def testScores418(self):
        """
        Checks that the getScoreList method returns a list of items with
        score within .1 points (Palermo scale) of score passed in
        """
        scores = json.loads(get_asteroids_by_score(4.18))
        self.assertIn({"AsteroidDesignation": "(2010 CR5)", "palermoScale": -4.27, "url": "http://localhost/asteroid/designation/%282010%20CR5%29"}, scores)
        self.assertIn({"AsteroidDesignation": "(2017 GM7)", "palermoScale": -4.27, "url": "http://localhost/asteroid/designation/%282017%20GM7%29"}, scores)

    """
    NEAR EARTH OBJECTS TESTS
    """

    def testListAllAsteroidsEmpty(self):
        # Get a list of all asteroid designations (names) in the database, make sure there are none
        self.assertFalse(len(get_neos())==0)

    def testAllNearEarthObjects(self):
        # Get a list of all near earth objects in the database
        earthObjects = json.loads(get_neos())
        self.assertIn({'object': '(1983 LC)', 'closeapproachdate': '2097-Jun-26 12:49ξαξξξξ00:02', 'cadistance': '13.34 | 0.03429', 'cadistancemin': '13.34 | 0.03427', 'vrelative': 18.85, 'url': 'http://localhost/neo/object/%281983%20LC%29'}, earthObjects)
        self.assertIn({'object': '(1983 LC)', 'closeapproachdate': '2177-Jul-06 19:05ξαξξξξ02:16', 'cadistance': '16.40 | 0.04213', 'cadistancemin': '15.72 | 0.04039', 'vrelative': 16.85, 'url': 'http://localhost/neo/object/%281983%20LC%29'}, earthObjects)
        self.assertIn({'object': '(1983 LC)', 'closeapproachdate': '1920-Jun-29 20:00ξαξ< 00:01', 'cadistance': '11.36 | 0.02919', 'cadistancemin': '11.36 | 0.02918', 'vrelative': 16.86, 'url': 'http://localhost/neo/object/%281983%20LC%29'}, earthObjects)

    def testDateNone(self):
        # Get a list of asteroid designations (names) with the given year.
        self.assertEqual(len(json.loads(get_neo_dates(1000))), 0)

    def testDate1900(self):
        '''
        Creates an instance of get_dates from the year 1900, which returns a
        list of objects that approached/will approach the earth in that year
        '''
        dates = json.loads(get_neo_dates(1900))
        self.assertEqual('378160 (2006 WX1)', dates[54]['object'])
        self.assertEqual('(2002 LY1)', dates[0]['object'])
        self.assertEqual('(2003 ED50)', dates[3]['object'])
        self.assertIn({'object': '(2003 FK1)', 'closeapproachdate': '1900-Mar-15 11:55ξαξξξξ13:41', 'url': 'http://localhost/neo/object/%282003%20FK1%29'}, dates)

    def testDatenotin1982(self):
        '''
        Creates an instance of get_dates from the year 1983, which returns a
        list of objects that approached/will approach the earth in that year
        '''
        dates = json.loads(get_neo_dates(1982))
        self.assertNotIn({'object': '(2003 FK1)', 'closeapproachdate': '1900-Mar-15 11:55ξαξξξξ13:41', 'url': 'http://localhost/neo/object/%282003%20FK1%29'}, dates)  # boundary case (Dec, 1981)
        self.assertNotIn({'object': '(1983 LC)', 'closeapproachdate': '1920-Jun-29 20:00ξαξ< 00:01', 'url': 'http://localhost/neo/object/%281983%20LC%29'}, dates)  # boundary case (Jan, 1983)

    def testGetDataNEO(self):
        '''
        Get a dictionary of data for a specific NEO given a designation
        '''
        dictionary = json.loads(get_neo_by_object("(2003 FK1)"))
        self.assertTrue(dictionary['hmag']==23)
        self.assertTrue(dictionary['vrelative']==14.78)

    def testDiameters(self):
        '''
        Get a dictionary of all close earth approaches (names) with it's diameters
        '''
        dictionary = json.loads(get_neo_distance())
        self.assertIn({'object': '99942 Apophis (2004 MN4)', 'estimateddi': '310 m -  680 m', 'url': 'http://localhost/neo/object/99942%20Apophis%20%282004%20MN4%29'},dictionary)


if __name__ == '__main__':
    unittest.main()