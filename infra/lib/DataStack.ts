import { Stack, StackProps } from 'aws-cdk-lib';
import { Construct } from 'constructs';
import { ManagedPolicy, Role, User } from 'aws-cdk-lib/aws-iam';

// import * as sqs from 'aws-cdk-lib/aws-sqs';

export class DataStack extends Stack {
	readonly USER_ARN = 'arn:aws:iam::709312312393:user/adamtrev-cli';

	readonly DYNAMO_DB_FULL_ACCESS_POLICY = 'arn:aws:iam::aws:policy/AmazonDynamoDBFullAccess';

	constructor(scope: Construct, id: string, props?: StackProps) {
		super(scope, id, props);

		// The code that defines your stack goes here

		// example resource
		// const queue = new sqs.Queue(this, 'InfraQueue', {
		//   visibilityTimeout: cdk.Duration.seconds(300)
		// });

		const dynamoRole = new Role(
			this,
			'DynamoRole',
			{
				assumedBy: User.fromUserArn(this, 'adamtrev-cli', this.USER_ARN)
		});

		dynamoRole.addManagedPolicy(ManagedPolicy.fromManagedPolicyArn(
			this,
			'DynamoManagedPolicy',
			this.DYNAMO_DB_FULL_ACCESS_POLICY
		));
	}
}
