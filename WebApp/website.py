#!/usr/bin/env python3
'''
    website.py
    Ethan Somes, Sonia Moreno, May 9, 2017

'''
import sys
import flask
import json
import config
import psycopg2

app = flask.Flask(__name__, static_folder='static', template_folder='templates')

@app.route('/') 
def get_main_page():
    ''' This is the only route intended for human users '''
    return flask.render_template('homepage.html')

@app.route('/info/<neo_object>')
def get_results_page(neo_object):
    ''' This is the only route intended for human users '''
    return flask.render_template('AsteroidOfDay.html', message=neo_object)

if __name__ == '__main__':
    if len(sys.argv) != 3:
        print('Usage: {0} host port'.format(sys.argv[0]), file=sys.stderr)
        exit()

    host = sys.argv[1]
    port = sys.argv[2]
    app.run(host=host, port=port)

