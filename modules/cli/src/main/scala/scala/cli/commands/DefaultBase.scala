package scala.cli.commands

import caseapp._
import caseapp.core.Error

class DefaultBase(
  defaultHelp: => String,
  defaultFullHelp: => String
) extends ScalaCommand[RunOptions] {

  override protected def commandLength = 0

  override def group                              = "Main"
  override def sharedOptions(options: RunOptions) = Some(options.shared)
  private[cli] var anyArgs                        = false
  override def helpAsked(progName: String, maybeOptions: Either[Error, RunOptions]): Nothing = {
    println(defaultHelp)
    sys.exit(0)
  }
  override def fullHelpAsked(progName: String): Nothing = {
    println(defaultFullHelp)
    sys.exit(0)
  }
  def run(options: RunOptions, args: RemainingArgs): Unit =
    if (anyArgs)
      Run.run(
        options,
        args
      )
    else
      helpAsked(finalHelp.progName, Right(options))
}