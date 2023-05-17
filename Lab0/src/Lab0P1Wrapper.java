public class Lab0P1Wrapper {

	public static interface BaseVector {
		public double getXComponent();

		public double getYComponent();

		public double getMagnitude();

		public double getAngle();

		public BaseVector add(BaseVector V);

		public BaseVector subtract(BaseVector V);

		public double dotProduct(BaseVector V);

		public BaseVector crossProduct(BaseVector V);
	}

	public static class VectorComponent implements BaseVector {

		private double X;
		private double Y;

		public VectorComponent(double X, double Y) {
			/* TODO ADD YOUR CODE HERE */
			this.X = X;
			this.Y = Y;
		}

		@Override
		public double getXComponent() {
			/* TODO ADD YOUR CODE HERE */
			return X;
		}

		@Override
		public double getYComponent() {
			/* TODO ADD YOUR CODE HERE */
			return Y;
		}

		public void setX(double x) {
			/* TODO ADD YOUR CODE HERE */
			this.X = x;
		}

		public void setY(double y) {
			/* TODO ADD YOUR CODE HERE */
			this.Y = y;
		}

		@Override
		public BaseVector add(BaseVector V) {
			/* TODO ADD YOUR CODE HERE */
			double newX_VectorComponent = this.getXComponent() + V.getXComponent(); 
			double newY_VectorComponent = this.getYComponent() + V.getYComponent();
			return new VectorComponent(newX_VectorComponent, newY_VectorComponent);
		}

		@Override
		public BaseVector subtract(BaseVector V) {
			/* TODO ADD YOUR CODE HERE */
			double newX_VectorComponent = this.getXComponent() - V.getXComponent();
			double newY_VectorComponent = this.getYComponent() - V.getYComponent();
			return new VectorComponent(newX_VectorComponent, newY_VectorComponent);
		}

		@Override
		public double dotProduct(BaseVector V) {
			/* TODO ADD YOUR CODE HERE */
			return (this.getXComponent() * V.getXComponent()) + (this.getYComponent() * V.getYComponent());
		}

		@Override
		public BaseVector crossProduct(BaseVector V) {
			/**
			 * TODO ADD YOUR CODE HERE
			 * 
			 * Can you do a cross product of 2D vectors?
			 * If not, what should we do?
			 * 
			 * Hint: Look up what UnsopportedOperationException does!
			 */
			throw new UnsupportedOperationException("Cross Product is not supported for 2D Vectors!");
		}

		@Override
		public double getMagnitude() {
			/* TODO ADD YOUR CODE HERE */
			return Math.sqrt(Math.pow(this.getXComponent(), 2) + Math.pow(this.getYComponent(), 2));
		}

		@Override
		public double getAngle() {
			/* TODO ADD YOUR CODE HERE */
			double angleInRadians = Math.atan2(this.getYComponent(), this.getXComponent());
			return Math.toDegrees(angleInRadians);
		}
	}
}
