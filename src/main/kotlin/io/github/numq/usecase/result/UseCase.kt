package io.github.numq.usecase.result

sealed interface UseCase<in Input, out Output> {
    suspend fun execute(input: Input): Output

    suspend operator fun invoke(input: Input): Result<Output> = runCatching { execute(input = input) }

    interface Action : UseCase<Unit, Unit> {
        suspend fun action()

        override suspend fun execute(input: Unit): Unit = action()

        suspend operator fun invoke(): Result<Unit> = invoke(input = Unit)
    }

    interface Query<out Output> : UseCase<Unit, Output> {
        suspend fun query(): Output

        override suspend fun execute(input: Unit): Output = query()

        suspend operator fun invoke(): Result<Output> = invoke(input = Unit)
    }

    interface Command<in Input> : UseCase<Input, Unit> {
        suspend fun command(input: Input)

        override suspend fun execute(input: Input): Unit = command(input = input)
    }

    interface Exchange<in Input, out Output> : UseCase<Input, Output> {
        suspend fun exchange(input: Input): Output

        override suspend fun execute(input: Input): Output = exchange(input = input)
    }
}