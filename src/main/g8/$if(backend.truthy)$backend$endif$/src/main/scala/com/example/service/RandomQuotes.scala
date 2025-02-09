package com.example.services

import zio._

trait RandomQuotes {
  def getRandomMessage: UIO[String]
}

private[services] final case class RandomQuotesLive(random: Random) extends RandomQuotes {
  override def getRandomMessage: UIO[String] =
    for {
      // 70% chance to get a quote, 30% chance to get a greeting
      isQuote <- random.nextDouble.map(_ < 0.7)
      result <- if (isQuote) {
                  random.nextIntBounded(programmingQuotes.length).map(programmingQuotes(_))
                } else {
                  random.nextIntBounded(greetings.length).map(greetings(_))
                }
    } yield result

  private val greetings = List(
    "Hey there! (English)",
    "你好！(Mandarin Chinese)",
    "¡Hola! (Spanish)",
    "नमस्ते! (Hindi)",
    "السلام عليكم! (Arabic)",
    "Bonjour ! (French)",
    "Olá! (Portuguese)",
    "Привет! (Russian)",
    "こんにちは! (Japanese)",
    "안녕하세요! (Korean)",
    "Hallo! (German)",
    "Ciao! (Italian)",
    "Cześć! (Polish)",
    "Hej! (Swedish)",
    "Xin chào! (Vietnamese)",
    "สวัสดี! (Thai)",
    "Hoi! (Dutch)",
    "Γεια σας! (Greek)",
    "Merhaba! (Turkish)",
    "שָׁלוֹם! (Hebrew)"
  )

  private val programmingQuotes = List(
    "Programs must be written for people to read, and only incidentally for machines to execute. - Harold Abelson",
    "First, solve the problem. Then, write the code. - John Johnson",
    "Simplicity is prerequisite for reliability. - Edsger W. Dijkstra",
    "The function of good software is to make the complex appear to be simple. - Grady Booch",
    "Pure functions are the atoms of programming. - Eric Elliott",
    "The essence of functional programming is thinking about programs as values. - Bartosz Milewski",
    "In functional programming, we think of a program as a stateless series of transformations on data. - Martin Odersky",
    "Functional programming is like describing your problem to a mathematician. - Unknown",
    "The joy of functional programming is that it lets you write down what you mean, rather than how to compute it. - Simon Peyton Jones",
    "Side effects are the biggest source of theoretical and practical complexity in software development. - Rich Hickey",
    "The problem with object-oriented languages is they've got all this implicit environment that they carry around with them. - John Carmack",
    "In FP, we don't tell the computer what to do – we tell it what stuff is. - Luca Matteis",
    "Functional programming is not about functions. It's about composition. - Rúnar Bjarnason",
    "The key to understanding recursion is to understand recursion. - Unknown",
    "Referential transparency is the ability to replace an expression with its value without changing the behavior of the program. - Philip Wadler",
    "The best programs are written so that computing machines can perform them quickly and so that human beings can understand them clearly. - Donald Knuth",
    "Immutability changes everything. - Pat Helland",
    "The most fundamental problem in software development is complexity. There is only one basic way of dealing with complexity: divide and conquer. - Bjarne Stroustrup",
    "The purpose of abstraction is not to be vague, but to create a new semantic level in which one can be absolutely precise. - Edsger W. Dijkstra",
    "The advantage of pure functions is that they are deterministic: given the same input, they always return the same output. - Martin Fowler"
  )
}

object RandomQuotes {
  val live: ULayer[RandomQuotes] = ZLayer(ZIO.random.map(new RandomQuotesLive(_)))
  def getRandomMessage: URIO[RandomQuotes, String] =
    ZIO.serviceWithZIO[RandomQuotes](_.getRandomMessage)
}
