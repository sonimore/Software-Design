'''
   neo_api.py
   Ethan Somes
   Nyla Worker
   Sonia Moreno
   May 8, 2017
'''
import sys
import flask
import json
import config
import psycopg2

app = flask.Flask(__name__, static_folder='static', template_folder='templates')

def _fetch_all_rows_for_query(query):
    '''
    :param query: 
    :return: a list of rows obtained from the books database by the specified SQL
        query. If the query fails for any reason, an empty list is returned.
        Note that this is not necessarily the right error-handling choice. Would users
        of the API like to know the nature of the error? Do we as API implementors
        want to share that information? There are many considerations to balance.
    '''

    try:
        connection = psycopg2.connect(database=config.database, user=config.user, password=config.password)
    except Exception as e:
        print('Connection error:', e, file=sys.stderr)
        return []

    rows = []
    try:
        cursor = connection.cursor()
        cursor.execute(query)
        rows = cursor.fetchall()  # This can be trouble if your query results are really big.
    except Exception as e:
        print('Error querying database:', e, file=sys.stderr)

    connection.close()
    return rows


@app.after_request
def set_headers(response):
    '''
    :param response 
    :return: response
    '''
    response.headers['Access-Control-Allow-Origin'] = '*'
    return response


@app.route('/neos/')
def get_neos():
    '''
    :return a list of all the authors in our database, in alphabetical
    order by last name, then first_name. See get_author_by_id below
    for description of the author resource representation.
    '''
    query = '''SELECT object, closeapproachdate, cadistance, cadistancemin, vrelative, vinfinity, 
               hmag, estimateddi,id FROM neo3 ORDER BY object, vrelative'''
    neo_list = []
    for row in _fetch_all_rows_for_query(query):
        with app.test_request_context():
            url = flask.url_for('get_neo_by_object', neo_object=row[0], _external=True)
        neo = {'object': row[0], 'closeapproachdate': row[1], 'cadistance': row[2], 'cadistancemin': row[3],
               'vrelative': row[4], 'url': url}
        neo_list.append(neo)

    return json.dumps(neo_list)


@app.route('/neo/object/<neo_object>')
def get_neo_by_object(neo_object):
    '''
    :param a neo object's name
    :return the neo resource that has the specified object name with the following info: object , 
        closeapproachdate, cadistance, cadistancemin, vrelative, vinfinity, hmag, estimateddi,id FROM neo3 WHERE object
    '''

    query = '''SELECT object , closeapproachdate, cadistance, cadistancemin, vrelative, 
              vinfinity, hmag, estimateddi,id FROM neo3 WHERE object = '{0}' '''.format(neo_object)

    rows = _fetch_all_rows_for_query(query)
    if len(rows) > 0:
        row = rows[0]
        with app.test_request_context():
            url = flask.url_for('get_neo_by_object', neo_object=row[0], _external=True)
        neo = {'object': row[0], 'closeapproachdate': row[1], 'cadistance': row[2], 'cadistancemin': row[3],
               'vrelative': row[4], 'vinfinity': row[5], 'hmag': row[6], 'estimateddi': row[7], 'id': row[8],
               'url': url}
        return json.dumps(neo)

    return json.dumps({})


@app.route('/neo/date/<int:year>')
def get_neo_dates(year):
    '''
    :param: year
    :return: name of all the objects with the same year as specified in the argument. 
    '''
    query = '''SELECT object, closeapproachdate FROM neo3 ORDER BY object'''
    neo_date_list = []
    for row in _fetch_all_rows_for_query(query):
        with app.test_request_context():
            url = flask.url_for('get_neo_by_object', neo_object=row[0], _external=True)
        neo = {'object': row[0], 'closeapproachdate': row[1], 'url': url}
        if (int(neo['closeapproachdate'].split(" ")[0].split("-")[0]) == year):
            neo_date_list.append(neo)
    return json.dumps(neo_date_list)


@app.route('/neos/diameter/')
def get_neo_distance():
    '''
    :return: how far each neo is away from the earth
    '''
    query = '''SELECT object, estimateddi FROM neo3 ORDER BY object'''
    neo_distance_list = []
    for row in _fetch_all_rows_for_query(query):
        with app.test_request_context():
            url = flask.url_for('get_neo_by_object', neo_object=row[0], _external=True)
        neo = {'object': row[0], 'estimateddi': row[1], 'url': url}
        neo_distance_list.append(neo)
    return json.dumps(neo_distance_list)


@app.route('/asteroids/')
def get_asteroids():
    '''
    :return a list of all the authors in our database, in alphabetical
    order by last name, then first_name. See get_author_by_id below
    for description of the author resource representation.
    '''
    query = '''SELECT AsteroidDesignation,Date,DistanceRE, SigmaImp, SigmaLOVr, SigmaLOV, ImpactProbability, 
    WidthRE, ImpactEn, palermoScale, torinoScale, id FROM asteroids ORDER BY AsteroidDesignation, SigmaImp'''
    print(query)
    asteroid_list = []
    for row in _fetch_all_rows_for_query(query):
        with app.test_request_context():
            url = flask.url_for('get_asteroid_by_designation', asteroid_designation=row[0], _external=True)
        asteroid = {'AsteroidDesignation': row[0], 'date': row[1], 'DistanceRE': row[2], 'SigmaImp': row[3],
                    'SigmaLOVr': row[4], 'ImpactProbability': row[5], 'WidthRE': row[6], 'estimateddi': row[7],
                    'ImpactEn': row[8],
                    'palermo_scale': row[9], 'torino_scale': row[10], 'id': row[11], 'url': url}
        asteroid_list.append(asteroid)

    return json.dumps(asteroid_list)


@app.route('/asteroid/designation/<asteroid_designation>')
def get_asteroid_by_designation(asteroid_designation):
    '''
        :param asteroid's name 
        :return the neo resource that has the specified object name with the following info: object , 
        closeapproachdate, cadistance, cadistancemin, vrelative, vinfinity, hmag, estimateddi,id FROM neo3 WHERE object
    '''

    query = '''SELECT AsteroidDesignation,Date,DistanceRE, SigmaImp, SigmaLOVr, SigmaLOV, ImpactProbability, 
    WidthRE, ImpactEn, palermoScale, torinoScale, id FROM asteroids WHERE AsteroidDesignation = '{0}' '''.format(
        asteroid_designation)

    rows = _fetch_all_rows_for_query(query)
    if len(rows) > 0:
        row = rows[0]
        with app.test_request_context():
            url = flask.url_for('get_asteroid_by_designation', asteroid_designation=row[0], _external=True)
        asteroid = {'AsteroidDesignation': row[0], 'date': row[1], 'DistanceRE': row[2], 'SigmaImp': row[3],
                    'SigmaLOVr': row[4], 'ImpactProbability': row[5], 'WidthRE': row[6], 'estimateddi': row[7],
                    'ImpactEn': row[8],
                    'palermo_scale': row[9], 'torino_scale': row[10], 'id': row[11], 'url': url}
        return json.dumps(asteroid)

    return json.dumps({})


@app.route('/asteroid/date/<int:year>')
def get_asteroid_dates(year):
    '''
    :param year: year that asteroids approached or will approach Earth
    :return: a list of asteroid designations with a close approach on the given year
    '''
    query = '''SELECT AsteroidDesignation, Date FROM asteroids ORDER BY AsteroidDesignation'''
    asteroid_date_list = []
    for row in _fetch_all_rows_for_query(query):
        with app.test_request_context():
            url = flask.url_for('get_asteroid_by_designation', asteroid_designation=row[0], _external=True)
        asteroid = {'AsteroidDesignation': row[0], 'date': row[1], 'url': url}
        if (int(asteroid['date'].split("-")[0]) == year):
            asteroid_date_list.append(asteroid)
    return json.dumps(asteroid_date_list)


@app.route('/asteroid/scores/<float:score>')
def get_asteroids_by_score(score):
    '''
    :param score (palermo scale): 
    :return: all asteroid designations with a specific score
    '''
    score = -score
    query = '''SELECT AsteroidDesignation, palermoScale FROM asteroids ORDER BY palermoScale '''
    asteroids = []
    for row in _fetch_all_rows_for_query(query):
        with app.test_request_context():
            url = flask.url_for('get_asteroid_by_designation', asteroid_designation=row[0], _external=True)
        asteroid = {'AsteroidDesignation': row[0], 'palermoScale': row[1], 'url': url}
        print(type(score))
        if (float(asteroid['palermoScale']) <= float(score + 0.1) and float(asteroid['palermoScale']) >= float(
                    score - 0.1)):
            asteroids.append(asteroid)
    return json.dumps(asteroids)


if __name__ == '__main__':
    if len(sys.argv) != 3:
        print('Usage: {0} host port'.format(sys.argv[0]), file=sys.stderr)
        exit()

    host = sys.argv[1]
    port = sys.argv[2]
    app.run(host=host, port=int(port), debug=True)

