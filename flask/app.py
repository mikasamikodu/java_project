from flask import Flask,render_template
import sqlite3

app = Flask(__name__)

@app.route("/")
def index():
    return render_template("index.html")

@app.route("/movie")
def movie():
    conn = sqlite3.connect("movie.db")
    cursor = conn.cursor()
    sql = "select * from movies"
    list = cursor.execute(sql)
    return render_template("movie.html", list=list)

@app.route("/score")
def score():
    return render_template("score.html")

@app.route("/word")
def word():
    return render_template("word.html")

@app.route("/team")
def team():
    return render_template("team.html")

if __name__ == '__main__':
    app.run()

