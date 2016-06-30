package org.palladiosimulator.editors.tabs.operations;

import java.util.Iterator;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IItemLabelProvider;

import org.palladiosimulator.pcm.repository.DataType;
import org.palladiosimulator.pcm.repository.ExceptionType;
import org.palladiosimulator.pcm.repository.Parameter;
import org.palladiosimulator.pcm.repository.provider.RepositoryItemProviderAdapterFactory;
import org.palladiosimulator.pcm.ui.provider.PalladioItemProviderAdapterFactory;

/**
 * @author roman
 * Responsibly for representation different Signature propertys (ReturnType, Parameters, Exceptions), as string
 */
public class ParameterRepresentation {

	/**
	 * @param DataType
	 * @param AdapterFactory
	 * @return String
	 */
	public String setDataTypeToString(DataType returnType) {

		String result = "";
		
		// RepositoryItemProviderAdapterFactory
		ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory();
		adapterFactory
				.addAdapterFactory(new RepositoryItemProviderAdapterFactory());

		PalladioItemProviderAdapterFactory decoratedFactory = new PalladioItemProviderAdapterFactory(
				adapterFactory);


		if (returnType != null) {
			IItemLabelProvider datatypeProvider = (IItemLabelProvider) decoratedFactory
					.adapt((Object) returnType, IItemLabelProvider.class);
			result = datatypeProvider.getText(returnType);
		}

		return result;
	}

	public String setParametersToString(EList<Parameter> parameters) {

		String result = "";

		for (Iterator<Parameter> it = parameters.iterator(); it.hasNext();) {
			result += it.next().getParameterName() + ", ";
		}
		return deleteComma(result);
	}

	/**
	 * Comma of the sentence deletes ends
	 */
	public String deleteComma(String result) {
		if (!result.equals("")) {
			result = result.substring(0, result.length() - 2);
		}
		return result;
	}

	public String setExceptionsToString(EList<ExceptionType> exceptions) {

		String result = "";
		ExceptionType exceptionType;

		for (Iterator<ExceptionType> it = exceptions.iterator(); it.hasNext();) {
			exceptionType = it.next();
			result += exceptionType.getExceptionName() + ", ";
		}
		return deleteComma(result);

	}


	public String isNotNull(String string) {
		return string == null ? "" : string;
	}
}
